package database.dao

import database.domain.UserEntity
import database.schema.DatabaseTables.{addressTable, userTable}
import javax.inject.{Inject, Singleton}
import models.UserWithAddresses
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MedicoDao @Inject()(dbConfigProvider: DatabaseConfigProvider)(
  implicit ec: ExecutionContext
) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  lazy val doctorQuery = userTable
    .filter(user => user.roles === UserEntity.Role.Doctor.id.toString)

  def getMedicos(name: Option[String]): Future[Seq[UserWithAddresses]] =
    db.run {
      val query = doctorQuery
        .filterOpt(name)(
          (user, value) =>
            user.firstName.toLowerCase
              .like(s"%${value.toLowerCase}%") || user.lastName.toLowerCase
              .like(s"%${value.toLowerCase}%")
        )
        .joinLeft(addressTable)
        .on(_.id === _.userId)
      query.result.map(
        _.map(
          userWithAddress =>
            UserWithAddresses(userWithAddress._1.toUser, userWithAddress._2)
        )
      )
    }

  def getMedicoById(doctorId: Long): Future[Option[UserEntity]] = db.run {
    doctorQuery.filter(_.id === doctorId).result.headOption
  }
}
