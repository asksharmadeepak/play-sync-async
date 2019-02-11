package services.simple.async

import models.{Credentials, UserToken}

import scala.concurrent.Future

/**
  * Created by dsm2061 on 11/02/19.
  */
trait SimpleAsyncTokenService {
  def requestToken(credentials: Credentials): Future[UserToken]
}
