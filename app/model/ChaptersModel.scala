package model

case class ChaptersModel(id: String, text:String, subChapters: SubChaptersModel) extends IChapters

case class SubChaptersModel(id: String, text: String, clauses: String) extends IChapters

trait IChapters {
  def id: String
  def text: String
}

