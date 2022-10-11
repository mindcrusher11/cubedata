package model

import org.jsoup.Jsoup


case class LegislationModel(LegislationVersionId: Int, LegislationSourceId: String, LegislationVersionOrdinal: Int,
                            Title: String, NativeTitle: Option[String], JurisdictionSourceId: String, JurisdictionName: String,
                            IssuingBodySourceId: String, IssuingBodyName: String, PartVersionId: Int, PartSourceId: String,
                            PartVersionOrdinal: Int, OrderNum: Int, Content: String, NativeContent: Option[String],
                            ParentPartVersionId: Int, ParentPartSourceId: String, ParentPartVersionOrdinal: Int)

object LegislationModel {

  def apply(LegislationVersionId: Int, LegislationSourceId: String, LegislationVersionOrdinal: Int,
            Title: String, NativeTitle: Option[String], JurisdictionSourceId: String, JurisdictionName: String,
            IssuingBodySourceId: String, IssuingBodyName: String, PartVersionId: Int, PartSourceId: String,
            PartVersionOrdinal: Int, OrderNum: Int, Content: String, NativeContent: Option[String],
            ParentPartVersionId: Int, ParentPartSourceId: String, ParentPartVersionOrdinal: Int) : LegislationModel  = {

    val text = Jsoup.parse(Content).text();

    new LegislationModel(LegislationVersionId, LegislationSourceId, LegislationVersionOrdinal,
      Title, NativeTitle, JurisdictionSourceId, JurisdictionName,
      IssuingBodySourceId, IssuingBodyName, PartVersionId, PartSourceId,
      PartVersionOrdinal, OrderNum, text, NativeContent,
      ParentPartVersionId, ParentPartSourceId, ParentPartVersionOrdinal)
  }
}

