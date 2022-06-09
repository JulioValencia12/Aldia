package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.apis.{AccountsAPI}
import io.gatling.core.Predef._

class GetAccountTest extends Simulation with AccountsAPI with Environment {

  val scenarioOk =
    scenario("get_account_by_id_ok")
      .exec(
        getAccountById( "988b6140-ddf5-11ec-bfb6-4984ba70d27f", dataChecksAccountById )
      )

  setUp(
    scenarioOk.inject( atOnceUsers(1) ) ,
  ).protocols(httpProtocol.disableWarmUp)
}