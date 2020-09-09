package models

import formatters.TimestampFormatter
import play.api.libs.json.Json

case class Speciality(id: Long,
                      speciality: String)

object Speciality extends TimestampFormatter {
  implicit val specialityFormat = Json.format[Speciality]
}
