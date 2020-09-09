package models

import play.api.libs.json.{Format, Json}

case class UserWithAddresses(user: User, address: Option[Address])

object UserWithAddresses {
  implicit val userWithAddressFormat: Format[UserWithAddresses] = Json.format[UserWithAddresses]
}
