package models

import formatters.TimestampFormatter
import play.api.libs.json.Json

case class Role(id: Long,
                role: String)

object Role extends TimestampFormatter {
  implicit val roleFormat = Json.format[Role]
}
