package controllers

import model.LegislationModel
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.OK
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, HOST, contentAsJson, contentAsString, defaultAwaitTimeout, route, status, writeableOf_AnyContentAsEmpty}
import utils.JsonFormats._

import scala.concurrent.Future


/**
 * Testing of apis using fake requests
 *
 * importing, search, read all data
 *
 * @author Gaurhari
 * */
class LegislationControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  "Legislation Controller should " should  {

    "read data from database" in {
      val request = FakeRequest(GET, "/legislation/all").withHeaders(HOST -> "localhost:9000")
      val legislationData: Future[Result] = route(app, request).get

      println(Json.fromJson[Seq[LegislationModel]](contentAsJson(legislationData)).get)
      status(legislationData) mustBe OK
      val legislationModel: Seq[LegislationModel] = Json.fromJson[Seq[LegislationModel]](contentAsJson(legislationData)).get
      legislationModel.head.Content.contains("Bureau") mustBe true
    }

    "read data from files" in {

      val request = FakeRequest(GET,
        "/legislation/importLegislation?path=/home/gaur/Downloads/Legislation/Legislation_822.json")
        .withHeaders(HOST -> "localhost:9000")

      val importData : Future[Result] = route(app, request).get

      status(importData) mustBe OK

      println(contentAsString(importData))
    }

    "search data from database " in {
      val request = FakeRequest(GET,
        "/legislation/search?textSearch=unchanged")
        .withHeaders(HOST -> "localhost:9000")

      val searchOutput : Future[Result] = route(app, request).get

      status(searchOutput) mustBe OK
      println(contentAsString(searchOutput))
    }
  }

}
