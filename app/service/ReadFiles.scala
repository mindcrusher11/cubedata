package service

import play.api.libs.json.{JsValue, Json}
import tservice.tReadFiles

import java.io.FileInputStream

/**
 *Singleton class for reading files
 *
 * @author Gaurhari
 * */
object ReadFiles extends tReadFiles{

  /**
  * function to read json files
  *
  * @param filePath path of json file
   *
   *@return Json
  * */
  def readJsonFile (filePath: String): JsValue ={
    val stream = new FileInputStream(filePath)
    val json = try {  (Json.parse(stream)) } finally { stream.close() }
    json
  }

}
