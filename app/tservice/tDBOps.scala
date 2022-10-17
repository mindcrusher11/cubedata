package tservice

import play.api.libs.json.JsValue

/**
 * trait for database operations
 *
 *  @author gaurhari
 * */
trait tDBOps {

  def readJsonFile (filePath: String): AnyRef

}
