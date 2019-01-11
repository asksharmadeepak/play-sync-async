package services.async

import javax.inject.Inject

import models.{Credentials, User, UserToken}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class SimpleAsyncTokenServiceImpl@Inject() (asyncTokenServiceImpl: AsyncTokenServiceImpl) extends SimpleAsyncTokenService {


  def requestToken(credentials: Credentials): Future[UserToken] = {
    val authenticatedUser: Future[User] = asyncTokenServiceImpl.authenticate(credentials)
    authenticatedUser.flatMap { user =>
      issueToken(user).map(x => x )
      issueToken(user)
    }
  }

    def issueToken(authenticatedUser: User): Future[UserToken] = {
      asyncTokenServiceImpl.issueToken(authenticatedUser)
    }
}
