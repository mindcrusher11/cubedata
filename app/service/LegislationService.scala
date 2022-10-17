package service

import model.LegislationModel
import play.api.libs.json.Json
import repository.LegislationRepository
import utils.JsonFormats._
import javax.inject.Inject
/**
 *
 * Service class for performing operations
 * legislation Data from database or any other operations on Legislation
 *
 * @author Gaurhari
 *
 * construcrtor
 *
 * @param legislationRepository
 * */
class LegislationService @Inject() (val legislationRepository: LegislationRepository) {

  /**
   * function to read Json file and convert it into list of model  objects and
   * save it into database
   *
   * @param path file path of json
   *
   * */
  def importLegislationFile(path: String): Unit ={
    val legislations = Json.fromJson[Seq[LegislationModel]](ReadFiles.readJsonFile(path))
    legislations.get.map(rd => legislationRepository.create(rd))
  }
}
