package services.sync

import javax.inject.Inject

import models.{Credentials, User, UserToken}
import services.async.{AsyncTokenServiceImpl, SimpleAsyncTokenService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class SimpleSyncTokenServiceImpl@Inject()(syncTokenServiceImpl: SyncTokenService) extends SimpleSyncTokenService {


  def requestToken(credentials: Credentials): Future[UserToken] = {
    val authenticatedUser: Future[User] = syncTokenServiceImpl.authenticate(credentials)
    authenticatedUser.flatMap { user =>
      issueToken(user).map(x => x )
      issueToken(user)
    }
  }

    def issueToken(authenticatedUser: User): Future[UserToken] = {
      asyncTokenServiceImpl.issueToken(authenticatedUser)
    }
}
