package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.apis.AccountsAPI
import io.gatling.core.Predef._

class GetPocketTest extends Simulation with AccountsAPI with Environment {

  val scenarioOk =
    scenario("get_pockets_by_id_ok")
      .exec(
        getPocketById("988b6140-ddf5-11ec-bfb6-4984ba70d27f", dataChecksPocketById)
      )

  setUp(
    scenarioOk.inject(atOnceUsers(1)),
  ).protocols(httpProtocol.disableWarmUp)

}
