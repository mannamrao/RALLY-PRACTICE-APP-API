package models

import java.sql.Timestamp

import formatters.TimestampFormatter
import play.api.libs.json.{Format, Json}

case class Service(id: Long,
                   service: String,
                   doctorId: Long,
                   minimumCost: Long,
                   maximumCost: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp)

object Service extends TimestampFormatter {
  implicit val serviceFormat: Format[Service] = Json.format[Service]
}
