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


@Singleton
class LegislationRepository @Inject() (
                        implicit executionContext: ExecutionContext,
                        val reactiveMongoApi: ReactiveMongoApi){
  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(_.collection("legislation"))

  def findAll(limit: Int = 100 ): Future[Seq[LegislationModel]] = {
      collection.flatMap(
      _.find(BSONDocument(), Option.empty[LegislationModel])
      .cursor[LegislationModel]()
      .collect[Seq](limit, Cursor.FailOnError[Seq[LegislationModel]]())
      )
  }

  def findOne(id: BSONObjectID): Future[Option[LegislationModel]] = {
      collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[LegislationModel]).one[LegislationModel])
  }

  def create(partsmodel: LegislationModel): Future[WriteResult] = {
      collection.flatMap(_.insert(ordered = false)
      .one(partsmodel))
  }

  def update(id: BSONObjectID, partsmodel: LegislationModel):Future[WriteResult] = {
      collection.flatMap(
      _.update(ordered = false).one(BSONDocument("_id" -> id),
      partsmodel
      ))
  }

  def delete(id: BSONObjectID):Future[WriteResult] = {
      collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1))
      )
  }

  def search(text: String):Future[Seq[LegislationModel]] = {
    text
      collection.flatMap(
      _.find(BSONDocument("$text" -> BSONDocument("$search" -> text)), Option.empty[LegislationModel])
      .cursor[LegislationModel]()
      .collect[Seq](-1, Cursor.FailOnError[Seq[LegislationModel]]())
      )
  }
  }
