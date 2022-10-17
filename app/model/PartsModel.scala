package model

/**
 * Model class for Parts Data in json file
 *
 * @author Gaurhari
 *
 * constructor
 * @param  id
 * @param  sections
 * @param  paragraphs
 * @param  clauses
 * @param  chapters
 *
 * */
case class PartsModel(id:String , sections: String, paragraphs: String,
                      clauses: String, chapters: Option[ChaptersModel] = None)



