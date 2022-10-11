package controllers

import model.PartsModel
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import reactivemongo.api.bson.BSONObjectID
import repository.LitigationMongoRepository
import utils.JsonFormats._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


@Singleton
class LitigationController @Inject()(
                                      implicit executionContext: ExecutionContext,
                                      val litigationRepository: LitigationMongoRepository,
                                      val controllerComponents: ControllerComponents)
  extends BaseController {

  def findAll():Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    litigationRepository.findAll().map {
      partsModel => Ok(Json.toJson(partsModel))
    }
  }

  def findOne(id:String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => litigationRepository.findOne(objectId).map {
        employee => Ok("Employee Details : "+Json.toJson(employee))
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }

  def create():Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
    println(request.body)
    request.body
    request.body.validate[PartsModel].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      partsModel => litigationRepository.create(partsModel).map {
        _ => Created("New Employee inserted" + Json.toJson(partsModel))
      }
    )
  }}

  def update(id: String):Action[JsValue]  = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[PartsModel].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      partsModel =>{
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) => litigationRepository.update(objectId, partsModel).map {
            _ => Ok("Successfully updated")
          }
          case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
        }
      }
    )
  }}

  def delete(id: String):Action[AnyContent]  = Action.async { implicit request => {
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => litigationRepository.delete(objectId).map {
        _ => Ok("Employee removed from the db")
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }}
}
