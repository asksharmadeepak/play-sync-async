package services.async

import java.util.Calendar
import javax.inject.Inject

import akka.actor.ActorSystem
import models.{Credentials, User, UserToken}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}
import scala.util.Random

 class AsyncTokenServiceImpl@Inject() (actorSystem: ActorSystem) extends AsyncTokenService{

  def authenticate(credentials: Credentials): Future[User] = {
    val promise: Promise[Either[String, User]] = Promise[Either[String, User]]()
    val timeToWait: Int = new Random().nextInt(50)
    actorSystem.scheduler.scheduleOnce(1.second){
      credentials match {
        case credential if (credential.username.toUpperCase == credential.password) => promise.success(Right(User(credential.username)))
        case _ => promise.success(Left("Invalid Credential"))
      }
    }
    promise.future.filter(_.isRight).map(_.right.get)
  }

  def issueToken(user: User): Future[UserToken] = {
    val promise: Promise[Either[String, UserToken]] = Promise[Either[String, UserToken]]()
    val timeToWait = new Random().nextInt(5000)
    actorSystem.scheduler.scheduleOnce(1.second) {
      user match {
        case validUser if user.userId.nonEmpty =>  validUser.userId.startsWith("A") match {
          case true => promise.success(Left("username should not starts with A"))
          case _ => promise.success(Right(UserToken(validUser.userId+"_"+Calendar.getInstance().getTime)))
        }
        case _ => promise.success(Left("Unauthenticated user"))
      }
    }
    promise.future.filter(_.isRight).map(_.right.get)
  }
}
