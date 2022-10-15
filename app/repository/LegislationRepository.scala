package repository

import model.{LegislationModel}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONObjectID}
import reactivemongo.api.commands.WriteResult
import utils.JsonFormats._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

/**
 * Repository for Legislation to perform operations over mongodb
 *
 * @author Gaurhari
 * */
@Singleton
class LegislationRepository @Inject() (
                        implicit executionContext: ExecutionContext,
                        val reactiveMongoApi: ReactiveMongoApi){

  /**
   * mongodb collection
   * */
  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(_.collection("legislation"))

  collection.map(rt => rt.create())

  /**
   * find all records from database
   *
   * @author Gaurhari
   *
   * @param limit number of records to be returned by default 100
   * */
  def findAll(limit: Int = 100 ): Future[Seq[LegislationModel]] = {
      collection.flatMap(
      _.find(BSONDocument(), Option.empty[LegislationModel])
      .cursor[LegislationModel]()
      .collect[Seq](limit, Cursor.FailOnError[Seq[LegislationModel]]())
      )
  }

  /**
   *
   * find single record from mongodb based on id
   *
   * @author Gaurhari
   *
   * @param  id
   *
   * @return LegisLationModel output
   * */
  def findOne(id: BSONObjectID): Future[Option[LegislationModel]] = {
      collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[LegislationModel]).one[LegislationModel])
  }

  /**
   * Insert data into database
   *
   * @author Gaurhari
   *
   * @param  LegislationModel as input
   *
   * @return Future
   * */
  def create(partsmodel: LegislationModel): Future[WriteResult] = {
      collection.flatMap(_.insert(ordered = false)
      .one(partsmodel))
  }

  /**
   *
   * update records based on id
   *@author Gaurhari
   *
   * @return result status
   * */
  def update(id: BSONObjectID, partsmodel: LegislationModel):Future[WriteResult] = {
      collection.flatMap(
      _.update(ordered = false).one(BSONDocument("_id" -> id),
      partsmodel
      ))
  }

  /**
   * delete records from mongodb based on id
   *
   * @param id of record
   * */
  def delete(id: BSONObjectID):Future[WriteResult] = {
      collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1))
      )
  }

  /**
   * search Database based on text in the fields of model
   *
   * @param text input string to search
   *
   * @return Seq[LegislationModel]
   * */
  def search(text: String):Future[Seq[LegislationModel]] = {
    text
      collection.flatMap(
      _.find(BSONDocument("$text" -> BSONDocument("$search" -> text)), Option.empty[LegislationModel])
      .cursor[LegislationModel]()
      .collect[Seq](-1, Cursor.FailOnError[Seq[LegislationModel]]())
      )
  }
  }
