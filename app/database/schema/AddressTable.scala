package database.schema

import java.sql.Timestamp

import database.schema.DatabaseTables.userTable
import slick.jdbc.PostgresProfile.api._
import models.Address

class AddressTable(tag: Tag) extends Table[Address](tag, "addresses") {
  def id = column[Long]("id", O.PrimaryKey)
  def address1 = column[String]("address1")
  def address2 = column[String]("address2")
  def phone = column[String]("phone")
  def zipCode = column[String]("zip_code")
  def latitude = column[Long]("latitude")
  def longitude = column[Long]("longitude")
  def userId = column[Long]("user_id")
  def createdAt = column[Timestamp]("created_at")
  def updatedAt = column[Timestamp]("updated_at")

  def user = foreignKey("userId", userId, userTable)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def * =
    (id, address1, address2, phone, zipCode, latitude, longitude, userId, createdAt, updatedAt) <>
      ((Address.apply _).tupled, Address.unapply)
}
