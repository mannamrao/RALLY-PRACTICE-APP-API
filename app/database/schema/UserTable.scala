package database.schema

import java.sql.Timestamp

import database.domain.UserEntity
import slick.jdbc.PostgresProfile.api._

class UserTable(tag: Tag) extends Table[UserEntity](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def email = column[String]("email")
  def password = column[String]("password")
  def apiKey = column[String]("api_key")
  def roles = column[String]("roles")
  def designation = column[String]("designation")
  def createdAt = column[Timestamp]("created_at")
  def updatedAt = column[Timestamp]("updated_at")

  def * =
    (id, firstName, lastName, email, password, apiKey, roles, designation, createdAt, updatedAt) <> ((UserEntity.apply _).tupled, UserEntity.unapply)
}
