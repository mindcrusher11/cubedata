package controllers

import model.PartsModel
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.OK
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, HOST, POST, contentAsJson, defaultAwaitTimeout, route, status, writeableOf_AnyContentAsEmpty}
import utils.JsonFormats._

import scala.concurrent.Future

class LitigationControllerSpec extends PlaySpec with GuiceOneAppPerTest {

  "Litigation controller " should {
    "render the list of litigations" in {
      val request = FakeRequest(GET, "/litigation/all").withHeaders(HOST -> "localhost:9000")
      val home: Future[Result] = route(app, request).get

      println(Json.fromJson[Seq[PartsModel]](contentAsJson(home)).get)
      status(home) mustBe OK
      //val posts: Seq[PartsModel] = Json.fromJson[Seq[PartsModel]](contentAsJson(home)).get
      //posts.filter(_.id == "1").head mustBe (PartsModel("1","/v1/posts/1", "title 1", "blog post 1" ))
    }

    "create a student" in {
      val parts = PartsModel("testId", "testsection", "testpara", "testclause")
      val request = FakeRequest(POST, "/litigation/createPart").
        withHeaders(HOST -> "localhost:9000").
        withBody(Json.toJson(parts))
      val partCreate: Future[Result] = route(app, request).get
      println(status(partCreate))
      /*val resultAsString = contentAsString(partCreate)
      println(partCreate.value.get.get)
      resultAsString mustBe """{}"""
    }
  }*/
    }
  }
}

/*class WithLitigationApplication(implicit mockedRepo: LitigationMongoRepository) extends WithApplication with Injecting {

  implicit val ec = inject[ExecutionContext]

  val messagesApi = inject[MessagesApi]

  implicit val controller = inject[ControllerComponents]

  val litigationController: LitigationController = new LitigationController(ec, mockedRepo, stubControllerComponents())

}*/
