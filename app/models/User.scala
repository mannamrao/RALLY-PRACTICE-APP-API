package models

import formatters.TimestampFormatter
import play.api.libs.json._

case class User(id: Long,
                firstName: String,
                lastName: String,
                email: String,
                designation: String)

object User extends TimestampFormatter {
  implicit val userFormat: Format[User] = Json.format[User]
}
