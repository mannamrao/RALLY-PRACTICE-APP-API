package models

import java.sql.Timestamp

import formatters.TimestampFormatter
import play.api.libs.json.Json

case class Address(id: Long,
                   address1: String,
                   address2: String,
                   phone: String,
                   zipCode: String,
                   latitude: Long,
                   longitude: Long,
                   userId: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp)

object Address extends TimestampFormatter {
  implicit val addressFormat = Json.format[Address]
}
