package utils

import model.{ChaptersModel, LegislationModel, PartsModel, SubChaptersModel}
import play.api.libs.json.{Format, Json, Reads, Writes}
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

/**
 * defining implicit conversion of model to json and back
 *
 * @author Gaurhari
 *
 * */
object JsonFormats {

  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val partFormat: Format[PartsModel] = Json.format[PartsModel]

  implicit def partReader: BSONDocumentReader[PartsModel] = Macros.reader[PartsModel]

  implicit def partWriter: BSONDocumentWriter[PartsModel] = Macros.writer[PartsModel]

  implicit def partRead: Reads[PartsModel] = Json.reads[PartsModel]

  implicit val chaptersFormat: Format[ChaptersModel] = Json.format[ChaptersModel]

  implicit def chaptersReader: BSONDocumentReader[ChaptersModel] = Macros.reader[ChaptersModel]

  implicit def chaptersWriter: BSONDocumentWriter[ChaptersModel] = Macros.writer[ChaptersModel]

  implicit val subChaptersFormat: Format[SubChaptersModel] = Json.format[SubChaptersModel]

  implicit def subChaptersReader: BSONDocumentReader[SubChaptersModel] = Macros.reader[SubChaptersModel]

  implicit def subChaptersWriter: BSONDocumentWriter[SubChaptersModel] = Macros.writer[SubChaptersModel]


  implicit val litigationFormat: Format[LegislationModel] = Json.format[LegislationModel]

  implicit def litigationReader: BSONDocumentReader[LegislationModel] = Macros.reader[LegislationModel]

  implicit def litigationWriter: BSONDocumentWriter[LegislationModel] = Macros.writer[LegislationModel]

  implicit def litigationRead: Reads[LegislationModel] = Json.reads[LegislationModel]

  /*implicit def TWrite: Writes[T] = Json.writes[T]

  implicit val TFormat: Format[LegislationModel] = Json.format[LegislationModel]

  implicit def litigationReader: BSONDocumentReader[LegislationModel] = Macros.reader[LegislationModel]

  implicit def litigationWriter: BSONDocumentWriter[LegislationModel] = Macros.writer[LegislationModel]

  implicit def litigationRead: Reads[LegislationModel] = Json.reads[LegislationModel]

  implicit def litigationWrite: Writes[LegislationModel] = Json.writes[LegislationModel]*/


}
