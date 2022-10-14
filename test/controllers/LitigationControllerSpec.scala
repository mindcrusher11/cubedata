package controllers

import model.PartsModel
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.OK
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.http.Status.CREATED
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, HOST, POST, contentAsJson, contentAsString, defaultAwaitTimeout, route, status, writeableOf_AnyContentAsEmpty}
import utils.JsonFormats._

import scala.concurrent.Future

class LitigationControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  "Litigation controller " should {
    "render the list of litigations" in {
      val request = FakeRequest(GET, "/litigation/all").withHeaders(HOST -> "localhost:9000")
      val partsData: Future[Result] = route(app, request).get

      println(Json.fromJson[Seq[PartsModel]](contentAsJson(partsData)).get)
      status(partsData) mustBe OK
      val parts: Seq[PartsModel] = Json.fromJson[Seq[PartsModel]](contentAsJson(partsData)).get
      parts.filter(_.id == "testId").head mustBe (PartsModel("testId", "testsection", "testpara", "testclause" ))
    }

    "create a student" in {
      val parts = PartsModel("testId", "testsection", "testpara", "testclause")
      val request = FakeRequest(POST, "/litigation/createPart").
        withHeaders(HOST -> "localhost:9000").
        withBody(Json.toJson(parts))
      val partCreate: Future[Result] = route(app, request).get
      //println(status(partCreate))
      status(partCreate) mustBe CREATED

      /*val resultAsString = contentAsString(partCreate)
      println(partCreate.value.get.get)
      println(resultAsString)*/
    }
  }
}


