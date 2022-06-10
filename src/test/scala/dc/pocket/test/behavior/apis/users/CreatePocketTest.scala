package dc.pocket.test.behavior.apis.users

import scala.util.Random
import dc.pocket.test.Environment
import dc.pocket.test.apis.AccountsAPI
import dc.pocket.test.domain.Pocket
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CreatePocketTest extends Simulation with AccountsAPI with Environment {

  val rnd = new Random()
  val r = scala.util.Random

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  val pocket: Pocket =
    Pocket(
      userId =("988b6140-ddf5-11ec-bfb6-4984ba70d27f"),
      PocketName = ("Test" +randomString(4) ),
      PocketDescription = ("Test" +randomString(3) ),
  )

  val scenarioOk =
    scenario("create_pocket_ok")
      .exec(
        createPocket( pocket,List.empty)
          .check(status.is(200))
      )

  setUp(
    scenarioOk.inject( atOnceUsers(1) ) ,
  ).protocols(httpProtocol.disableWarmUp)
}