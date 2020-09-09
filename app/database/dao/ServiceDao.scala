package database.dao

import database.schema.DatabaseTables.serviceTable
import javax.inject.{Inject, Singleton}
import models.Service
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ServiceDao @Inject() (dbConfigProvider: DatabaseConfigProvider)
                           (implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  def getServicesByDoctorId(doctorId: Long): Future[Seq[Service]] = db.run {
    serviceTable.filter(_.doctorId === doctorId).result
  }
}
