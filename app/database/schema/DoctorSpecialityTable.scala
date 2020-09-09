package database.schema

import database.schema.DatabaseTables.{specialityTable, userTable}
import models.MedicoSpeciality
import slick.jdbc.PostgresProfile.api._

class DoctorSpecialityTable(tag: Tag)
    extends Table[MedicoSpeciality](tag, "doctor_specialities") {
  def doctorId = column[Long]("doctor_id", O.PrimaryKey)
  def specialityId = column[Long]("speciality_id")

  def doctor =
    foreignKey("doctorId", doctorId, userTable)(
      _.id,
      onUpdate = ForeignKeyAction.Restrict,
      onDelete = ForeignKeyAction.Cascade
    )
  def speciality =
    foreignKey("specialityId", specialityId, specialityTable)(
      _.id,
      onUpdate = ForeignKeyAction.Restrict,
      onDelete = ForeignKeyAction.Cascade
    )

  def * =
    (doctorId, specialityId) <> ((MedicoSpeciality.apply _).tupled, MedicoSpeciality.unapply)
}
