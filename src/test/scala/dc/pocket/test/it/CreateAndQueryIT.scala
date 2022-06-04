package dc.pocket.test.it

import dc.pocket.test.Environment
import dc.pocket.test.apis.UserAPI
import io.gatling.core.Predef._

class CreateAndQueryIT extends Simulation with UserAPI with AccountAPI with Environment {

  val scenarioOk =
    scenario("get_user_by_id_ok")
    .exec(
      createUser(user, checks)
    )
      .exec(
        createUser(user, checks)
      )
      .exec(
        transferMoney(user1, user2, 1000)
      )


  setUp(
    scenarioOk.inject( atOnceUsers(1) )
  ).protocols(httpProtocol.disableWarmUp)
}