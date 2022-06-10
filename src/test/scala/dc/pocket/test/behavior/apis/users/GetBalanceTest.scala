package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.apis.AccountsAPI
import io.gatling.core.Predef._
import io.gatling.http.Predef.status
import io.gatling.http.Predef._

class GetBalanceTest extends Simulation with AccountsAPI with Environment {

  val scenarioOk =
    scenario("get_balance_by_accountNumber_ok")
      .exec(
        getBalanceByAccount("1653680745158", dataChecksBalance)
      )

  val scenarioKo1 =
    scenario("get_balance_by_accountNumber_ko")
      .exec(
        getBalanceByAccount("1653680745000", dataChecksBalance)
          .check(status.is(session => 404))
      )


  val scenarioKo2 =
    scenario("get_balance_by_accountNumber_ko2")
     .exec(
       getBalanceByAccount("abd0fgh0jkl0", dataChecksBalance)
         .check(status.is(session => 404))
      )

  setUp(
    scenarioOk.inject(atOnceUsers(1)),
    scenarioKo1.inject(atOnceUsers(1)),
    scenarioKo2.inject(atOnceUsers(1)),
  ).protocols(httpProtocol.disableWarmUp)

}
