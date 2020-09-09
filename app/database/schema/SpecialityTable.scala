package database.schema

import slick.jdbc.PostgresProfile.api._

import models.Speciality

class SpecialityTable(tag: Tag) extends Table[Speciality](tag, "specialities") {
  def id = column[Long]("id", O.PrimaryKey)
  def speciality = column[String]("speciality")

  def * =
    (id, speciality) <> ((Speciality.apply _).tupled, Speciality.unapply)
}
