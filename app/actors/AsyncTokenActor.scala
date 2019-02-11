package actors

import javax.inject.Inject

import akka.actor.Actor
import models.Credentials
import services.async.AsyncTokenService

/**
  * Created by dsm2061 on 11/02/19.
  */
class AsyncTokenActor @Inject()(asyncTokenService: AsyncTokenService) extends Actor{

  override def receive: Receive = {
    case "authenticate" => asyncTokenService.requestToken(Credentials("house", "HOUSE"))
    case _       => println("huh?")
  }

}


/*
* //
//  def generateTokenAsync = Action.async{
//    simpleAsyncTokenServiceImpl.requestToken(Credentials("house", "HOUSE")).map {
//      asyncToken => Ok(asyncToken.token)
//    }
//  }
//
//  def generateTokenSync = Action.async{
//    simpleSyncTokenServiceImpl.requestToken(Credentials("house", "HOUSE")).map {
//      asyncToken => Ok(asyncToken.token)
//    }
//  }
//
* */