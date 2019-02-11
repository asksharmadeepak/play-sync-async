import actors.SyncTokenActor
import akka.actor.{ActorSystem, Props}

class AppLauncher extends App{

  val syncTokenSystem = ActorSystem("SyncTokenSystem")
  // default Actor constructor
  val syncTokenActor = syncTokenSystem.actorOf(Props[SyncTokenActor], name = "syncTokenActor")
  syncTokenActor ! "authenticate"
  syncTokenActor ! "buenos dias"


  val asyncTokenSystem = ActorSystem("AsyncTokenSystem")
  // default Actor constructor
  val asyncTokenActor = asyncTokenSystem.actorOf(Props[SyncTokenActor], name = "asyncTokenActor")
  asyncTokenActor ! "authenticate"
  asyncTokenActor ! "buenos dias"


}
