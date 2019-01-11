package controllers

import javax.inject.Inject

import models.Credentials

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc.{Action, Controller}
import services.async.SimpleAsyncTokenServiceImpl
import services.sync.SimpleSyncTokenServiceImpl

class SimpleAsyncTokenController@Inject() (simpleAsyncTokenServiceImpl: SimpleAsyncTokenServiceImpl,
                                           simpleSyncTokenServiceImpl: SimpleSyncTokenServiceImpl) extends Controller {

  def generateTokenAsync = Action.async{
    simpleAsyncTokenServiceImpl.requestToken(Credentials("house", "HOUSE")).map {
      asyncToken => Ok(asyncToken.token)
    }
  }

  def generateTokenSync = Action.async{
    simpleSyncTokenServiceImpl.requestToken(Credentials("house", "HOUSE")).map {
      asyncToken => Ok(asyncToken.token)
    }
  }

}
