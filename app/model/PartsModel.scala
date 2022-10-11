package model

case class PartsModel(id:String , sections: String, paragraphs: String, clauses: String, chapters: Option[ChaptersModel] = None)

/*
object PartsModel {

  // ALTERNATE CONSTRUCTOR #1 (without numClicks)
  def apply(
             id:String , sections: String, paragraphs: String, clauses: String
           ): PartsModel = {
    PartsModel(id:String , sections: String, paragraphs: String, clauses: String, None)
  }
*/

  // this is for something else, but i left it here as an `unapply` example


//chapters: ChaptersModel,



