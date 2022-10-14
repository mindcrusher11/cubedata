package service

import model.LegislationModel
import play.api.libs.json.Json
import repository.LegislationRepository
import utils.JsonFormats._

import javax.inject.Inject

class LegislationService @Inject() (val legislationRepository: LegislationRepository) {

  def importLegislationFile(path: String): Unit ={
    val legislations = Json.fromJson[Seq[LegislationModel]]( ReadFiles.readJsonFile(path))
    legislations.get.map(rd => legislationRepository.create(rd))
  }
}
