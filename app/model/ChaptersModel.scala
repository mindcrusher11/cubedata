package model

/**
 *
 * case class for defining model for chapters which extends trait of ichapter
 *
 * @author Gaurhari
 *
 * */
case class ChaptersModel(id: String, text:String, subChapters: SubChaptersModel) extends IChapters


/**
 * case class for subchapeters model
 *
 * @author Gaurhari
 * */
case class SubChaptersModel(id: String, text: String, clauses: String) extends IChapters


/**
 *
 * interface for chapters model
 *
 * */
trait IChapters {
  def id: String
  def text: String
}

