package database.domain

import java.sql.Timestamp

import models.User

case class UserEntity(id: Long,
                firstName: String,
                lastName: String,
                email: String,
                password: String,
                apiKey: String,
                roles: String,
                designation: String,
                createdAt: Timestamp,
                updatedAt: Timestamp) {
  def toUser: User = User(id, firstName, lastName, email, designation)
}

object UserEntity {
  object Role extends Enumeration {
    type Role = Value
    val Admin, Doctor, User = Value
  }
}
