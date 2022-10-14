package service

import play.api.libs.json.{JsValue, Json}
import tservice.tReadFiles

import java.io.FileInputStream
/**
 *
 * */
object ReadFiles extends tReadFiles{

  def readJsonFile (filePath: String): JsValue ={
    val stream = new FileInputStream(filePath)
    val json = try {  (Json.parse(stream)) } finally { stream.close() }
    //val json = try {  Json.fromJson[Seq[T]](Json.parse(stream)) } finally { stream.close() }
    json
  }

  def main(args:Array[String]): Unit = {
    readJsonFile("/home/gaur/Downloads/Legislation/Legislation_822.json")
  }
}
