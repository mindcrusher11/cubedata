package model

import com.google.common.collect.Table
import org.jsoup.Jsoup


/**
 * Model for Legislation class
 *
 * @author Gaurhari
 *
 * */
case class LegislationModel(LegislationVersionId: Int, LegislationSourceId: String, LegislationVersionOrdinal: Int,
                            Title: String, NativeTitle: Option[String], JurisdictionSourceId: String, JurisdictionName: String,
                            IssuingBodySourceId: String, IssuingBodyName: String, PartVersionId: Int, PartSourceId: String,
                            PartVersionOrdinal: Int, OrderNum: Int, Content: String, NativeContent: Option[String],
                            ParentPartVersionId: Int, ParentPartSourceId: String, ParentPartVersionOrdinal: Int)


/**
 * Companion class of LeghislationModel
 *
 * @author Gaurhari
 *
 * json implementation and bson implementation of case class and clean html from fields
 * */
object LegislationModel {

  /**
   * function to update fields of case class
   *
   * remove html from the fields
   * */
  def apply(LegislationVersionId: Int, LegislationSourceId: String, LegislationVersionOrdinal: Int,
            Title: String, NativeTitle: Option[String], JurisdictionSourceId: String, JurisdictionName: String,
            IssuingBodySourceId: String, IssuingBodyName: String, PartVersionId: Int, PartSourceId: String,
            PartVersionOrdinal: Int, OrderNum: Int, Content: String, NativeContent: Option[String],
            ParentPartVersionId: Int, ParentPartSourceId: String, ParentPartVersionOrdinal: Int) : LegislationModel  = {

    val text = Jsoup.parse(Content).text()
    val title = Jsoup.parse(Title).text()

    new LegislationModel(LegislationVersionId, LegislationSourceId, LegislationVersionOrdinal,
      title, NativeTitle, JurisdictionSourceId, JurisdictionName,
      IssuingBodySourceId, IssuingBodyName, PartVersionId, PartSourceId,
      PartVersionOrdinal, OrderNum, text, NativeContent,
      ParentPartVersionId, ParentPartSourceId, ParentPartVersionOrdinal)
  }
}



