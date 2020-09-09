package controllers

import javax.inject._
import models.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents,
  userService: UserService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def signin = Action.async { implicit request =>
    val form = registerUserConstraints.bind(request.body.asJson.get)
    if (form.hasErrors) {
      val messages = form.errors.map(error => error.key + " " + error.messages)
      Future(BadRequest(Json.toJson(messages)))
    } else {
      val firstName: String = form.data("firstName")
      val email: String = form.data("email")
      val password: String = form.data("password")
      val user = User(
        id = -1,
        firstName = firstName,
        lastName = "",
        email = email,
        designation = ""
      )
      val error = Map("error" -> "User already exists")

      userService.create(user, password).map {
        case Right(userEntity) => Ok(Json.toJson(userEntity.toUser))
        case Left(_)           => Conflict(Json.toJson(error))
      }
    }
  }

  private def registerUserConstraints = Form(
    tuple(
      "firstName" -> text.verifying(nonEmpty),
      "email" -> email.verifying(nonEmpty),
      "password" -> text.verifying(nonEmpty)
    )
  )
}
