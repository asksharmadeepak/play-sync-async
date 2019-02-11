package services.async

import models.{Credentials, User, UserToken}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by deepak.
  */
trait AsyncTokenService {

  protected def authenticate(credentials: Credentials): Future[User]
  protected def issueToken(user: User): Future[UserToken]

  def requestToken(credentials: Credentials): Future[UserToken] = {
    val authenticatedUser: Future[User] = authenticate(credentials)
    authenticatedUser.flatMap { user =>
      issueToken(user).map(x => x )
      issueToken(user)
    }
  }
}
