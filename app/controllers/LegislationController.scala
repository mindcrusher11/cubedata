package controllers

import model.{LegislationModel}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import reactivemongo.api.bson.BSONObjectID
import repository.{LegislationRepository, LitigationMongoRepository}
import service.LegislationService
import utils.JsonFormats._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
 * @author Gaurhari
 * */
@Singleton
class LegislationController @Inject()(
                                       implicit executionContext: ExecutionContext,
                                       val legislationRepository: LegislationRepository,
                                       val legislationService: LegislationService,
                                       val controllerComponents: ControllerComponents)
  extends BaseController {

  def findAll():Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    legislationRepository.findAll().map {
      partsModel => Ok(Json.toJson(partsModel))
    }
  }

  def findOne(id:String):Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => legislationRepository.findOne(objectId).map {
        employee => Ok("Employee Details : "+Json.toJson(employee))
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }

  def create():Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
    println(request.body)
    request.body
    request.body.validate[LegislationModel].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      partsModel => legislationRepository.create(partsModel).map {
        _ => Created("New Employee inserted" + Json.toJson(partsModel))
      }
    )
  }}

  def update(id: String):Action[JsValue]  = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[LegislationModel].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      partsModel =>{
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) => legislationRepository.update(objectId, partsModel).map {
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
      case Success(objectId) => legislationRepository.delete(objectId).map {
        _ => Ok("Employee removed from the db")
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }}
  /**
   * @author Gaurhari
   * */
  def search(textSearch: Option[String]):Action[AnyContent] = Action.async {
    implicit request => {
      val searchText = request.getQueryString("textSearch") match {
        case Some(x) => x
      }
      legislationRepository.search(searchText).map(dt => Ok(Json.toJson(dt)))
      }
    }

  def importData(path: String): Action[AnyContent] = Action.async {
    implicit request => {
      val filePath = request.getQueryString("path").get
      legislationService.importLegislationFile(filePath)
      Future.successful(Ok("file imported to database"))
    }
  }
  }
