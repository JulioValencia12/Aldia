package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.behavior.apis.UserAPI
import io.gatling.core.Predef._

class GetUserTest  extends Simulation with UserAPI with Environment {

  val scenarioOk =
    scenario("get_user_by_id_ok")
    .exec(
      getUserById( "988b6140-ddf5-11ec-bfb6-4984ba70d27f", dataChecks )
    )

  setUp(
    scenarioOk.inject( atOnceUsers(1) )
  ).protocols(httpProtocol)
}