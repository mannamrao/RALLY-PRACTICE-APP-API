package database.dao

import database.schema.DatabaseTables.{doctorSpecialityTable, specialityTable}
import javax.inject.{Inject, Singleton}
import models.Speciality
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SpecialityDao @Inject() (dbConfigProvider: DatabaseConfigProvider)
                           (implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  def getSpecialitiesByDoctorId(doctorId: Long): Future[Seq[Speciality]] = db.run {
    doctorSpecialityTable.filter(_.doctorId === doctorId)
      .map(d => (d.doctorId, d.specialityId))
      .join(specialityTable).on(_._2 === _.id)
      .map(a => a._2)
      .result
  }
}
