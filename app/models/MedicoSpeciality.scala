package models

import formatters.TimestampFormatter
import play.api.libs.json.Json

case class MedicoSpeciality(doctorId: Long, specialityId: Long)

object MedicoSpeciality extends TimestampFormatter {
  implicit val doctorSpecialityFormat = Json.format[MedicoSpeciality]
}
