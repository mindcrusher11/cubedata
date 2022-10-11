package service

import model.LegislationModel
import play.api.libs.json.Json
import tservice.tReadFiles
import utils.JsonFormats._

import java.io.FileInputStream
/**
 *
 * */
object ReadFiles extends tReadFiles{

  def readJsonFile(filePath: String): Unit ={
    val stream = new FileInputStream(filePath)
    val json = try {  Json.fromJson[Seq[LegislationModel]](Json.parse(stream)) } finally { stream.close() }
  }

  def main(args:Array[String]): Unit = {
    readJsonFile("/home/gaur/Downloads/Legislation/Legislation_822.json")
  }
}
