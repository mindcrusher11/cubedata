package controllers

import model.LegislationModel
import play.api.libs.json.JsResult.Exception
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import reactivemongo.api.bson.BSONObjectID
import repository.LegislationRepository
import service.LegislationService
import utils.JsonFormats._

import javax.inject.{Inject, Singleton}
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.postfixOps
import scala.util.{Failure, Success}

/**
 *
 * Controller class for defining apis on legislation data
 *
 * @author Gaurhari
 *
 * @param executionContext
 * @param legislationService
 * @param legislationRepository
 * @param controllerComponents
 *
 *
 * */
@Singleton
class LegislationController @Inject()(
                                       implicit executionContext: ExecutionContext,
                                       val legislationRepository: LegislationRepository,
                                       val legislationService: LegislationService,
                                       val controllerComponents: ControllerComponents)
  extends BaseController {

  /**
   * function to get all records from database
   *
   * @return Json of legislation records
   * */
  def findAll():Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
    legislationRepository.findAll().map {
      partsModel => Ok(Json.toJson(partsModel))
    }
  }

  /**
   * function get single record based on id
   *
   * @param id as string
   *
   * @return Json
   * */
  def findOne(id:String):Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => legislationRepository.findOne(objectId).map {
        legislation => Ok("Employee Details : "+Json.toJson(legislation))
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }

  /**
   * insert new records to mongodb
   * */
  def create():Action[JsValue] = Action.async(controllerComponents.parsers.json) {
    implicit request => {
    request.body.validate[LegislationModel].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      partsModel => legislationRepository.create(partsModel).map {
        _ => Created("New Legislation inserted" + Json.toJson(partsModel))
      }
    )
    }
  }

  /**
   * update data in database
   *
   * @param id for updating records
   * @param json input of model
   *
   * */
  def update(id: String):Action[JsValue]  = Action.async(controllerComponents.parsers.json) {
    implicit request => {
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

  /**
  * delete records from database based on input data
   *
   * @param request
  * */
  def delete(id: String):Action[AnyContent]  = Action.async {
    implicit request => {
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => legislationRepository.delete(objectId).map {
        _ => Ok("Employee removed from the db")
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the employee id"))
    }
  }}
  /**
   *
   * Search database using text in text based fields
   * @author Gaurhari
   *
   * @param textSearch
   *
   * @return Json output
   * */
  def search(textSearch: Option[String]):Action[AnyContent] = Action.async {
    implicit request => {
      val searchText = textSearch.getOrElse("", throw new RuntimeException("please input text to search"))

      legislationRepository.search(textSearch.get).onComplete {
        case Success(value) => println(s"data is retrieve in search request = $value")
        case Failure(e) => BadRequest("something wrong happened")
      }

      val searchData = legislationRepository.search(searchText.toString)
        .map(dt => Ok(Json.toJson(dt)))

      searchData.onComplete {
        case Success(value) =>println(s"data is retrieve in search request = $value")
        case Failure(e) => BadRequest("something wrong happened")
      }

      searchData
      }
    }

  /**
  * import data into database using file input
  *
  * @author Gaurhari
   *
   * @example /home/gaur/Downloads/Legislation/Legislation_822.json
   *
   *@return Status
   *
  *
  * */
  def importData(path: String): Action[AnyContent] = Action.async {
    implicit request => {
      legislationService.importLegislationFile(path)
      Future.successful(Ok("file imported to database"))
    }
  }
  }
