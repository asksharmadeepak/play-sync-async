package services.sync

import models.{Credentials, User, UserToken}

trait SyncTokenService {

  protected def authenticate(credentials: Credentials): User
  protected def issueToken(user: User): UserToken

  def requestToken(credentials: Credentials): UserToken = {
    val authenticatedUser: User = authenticate(credentials)
    issueToken(authenticatedUser)
  }
}