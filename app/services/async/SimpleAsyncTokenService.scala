package services.async

import models.{Credentials, UserToken}

import scala.concurrent.Future

/**
  * Created by dsm2061 on 12/5/18.
  */
trait SimpleAsyncTokenService {
  def requestToken(credentials: Credentials): Future[UserToken]
}
