package database.dao

import java.sql.Timestamp
import java.util.{Date, UUID}

import database.domain.UserEntity
import database.schema.DatabaseTables.userTable
import javax.inject.{Inject, Singleton}
import models.User
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import com.github.t3hnar.bcrypt._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

@Singleton
class UserDao @Inject() (dbConfigProvider: DatabaseConfigProvider) (implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  def getByApiKey(apiKey: String): Future[Option[UserEntity]] = db.run {
    userTable.filter(user => user.apiKey === apiKey).result.headOption
  }

  def getByEmail(email: String): Future[Option[UserEntity]] = db.run {
    userTable.filter(user => user.email === email).result.headOption
  }

  def create(user: User, password: String): Future[UserEntity] = db.run {
    val apiKey = UUID.randomUUID.toString
    val timestamp = new Timestamp(new Date().getTime)

    (userTable.map(u => (u.firstName, u.lastName, u.email, u.password, u.apiKey, u.roles, u.designation, u.createdAt, u.updatedAt))
      returning userTable.map(_.id)
      into ((cols, id) => UserEntity(id, cols._1, cols._2, cols._3, cols._4, cols._5, cols._6, cols._7, cols._8, cols._9))
      ) += (user.lastName, user.firstName, user.email, password.boundedBcrypt, apiKey, UserEntity.Role.User.id.toString, user.designation, timestamp, timestamp)
  }

  def getByEmailAndPassword(email: String, password: String): Future[Option[UserEntity]] = {
    val query = userTable
      .filter(user => user.email === email && user.roles === UserEntity.Role.User.id.toString)
      .result
      .headOption

    db.run(query).map {
      case Some(user) => password.isBcryptedSafeBounded(user.password) match {
        case Success(true) => Some(user)
        case _ => None
      }
      case _ => None
    }
  }
}
