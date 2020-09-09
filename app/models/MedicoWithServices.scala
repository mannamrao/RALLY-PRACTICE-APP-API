package models

import play.api.libs.json.{Format, Json}

case class MedicoWithServicesAndSpecialities(medico: User,
                                             services: Seq[Service],
                                             specialities: Seq[Speciality])

object MedicoWithServicesAndSpecialities {
  implicit val format: Format[MedicoWithServicesAndSpecialities] =
    Json.format[MedicoWithServicesAndSpecialities]
}
