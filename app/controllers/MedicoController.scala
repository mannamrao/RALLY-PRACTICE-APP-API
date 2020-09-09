package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.MedicoService

import scala.concurrent.ExecutionContext

@Singleton
class MedicoController @Inject()(
  val controllerComponents: ControllerComponents,
  medicoService: MedicoService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def getMedicos(name: Option[String]) = Action.async {
    medicoService.getDoctors(name).map(doctors => Ok(Json.toJson(doctors)))
  }

  def getMedicoById(doctorId: Long) = Action.async {
    medicoService
      .getMedicoWithServicesAndSpecialities(doctorId)
      .map(services => Ok(Json.toJson(services)))
  }
}
