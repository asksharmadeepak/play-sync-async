package actors

import javax.inject.Inject

import akka.actor.Actor
import models.Credentials
import services.sync.SyncTokenService

/**
  * Created by dsm2061 on 11/02/19.
  */
class SyncTokenActor @Inject()(syncTokenService: SyncTokenService) extends Actor{

  override def receive: Receive = {
    case "authenticate" => syncTokenService.requestToken(Credentials("house", "HOUSE"))
    case _       => println("huh?")
  }
}
