package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.apis.AccountsAPI
import io.gatling.core.Predef._

class GetBalanceTest extends Simulation with AccountsAPI with Environment {

  val scenarioOk =
    scenario("get_balance_by_accountNumber_ok")
      .exec(
        getBalanceByAccount("1653680745158", dataChecksBalance)
      )

  setUp(
    scenarioOk.inject(atOnceUsers(1)),
  ).protocols(httpProtocol.disableWarmUp)

}