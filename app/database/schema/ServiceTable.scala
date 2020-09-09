package database.schema

import java.sql.Timestamp

import models.Service
import slick.jdbc.PostgresProfile.api._

class ServiceTable(tag: Tag) extends Table[Service](tag, "services") {
  def id = column[Long]("id", O.PrimaryKey)
  def service = column[String]("service")
  def doctorId = column[Long]("doctor_id")
  def minimumCost = column[Long]("minimum_cost")
  def maximumCost = column[Long]("maximum_cost")
  def createdAt = column[Timestamp]("created_at")
  def updatedAt = column[Timestamp]("updated_at")

  def * =
    (id, service, doctorId, minimumCost, maximumCost, createdAt, updatedAt) <> ((Service.apply _).tupled, Service.unapply)
}
