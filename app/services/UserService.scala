package services

import database.dao.UserDao
import database.domain.UserEntity
import javax.inject.Inject
import models.User

import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject()(userDao: UserDao)(implicit ex: ExecutionContext) {

  def create(user: User, password: String): Future[Either[UserAlreadyExists, UserEntity]] = {
    userDao.getByEmail(user.email).flatMap {
      case None => userDao.create(user, password).map(Right(_))
      case _ => Future.successful(Left(UserAlreadyExists()))
    }
  }
}

case class UserAlreadyExists()
