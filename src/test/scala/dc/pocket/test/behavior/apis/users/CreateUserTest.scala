package dc.pocket.test.behavior.apis.users

import dc.pocket.test.Environment
import dc.pocket.test.behavior.apis.UserAPI
import dc.pocket.test.domain.User
import io.gatling.core.Predef._
import io.gatling.http.Predef.status
import io.gatling.http.check.HttpCheck

class CreateUserTest extends Simulation with UserAPI with Environment {

  val user: User =
    User(
      firstName = "",
      secondName = "",
      lastName = "",
      birthDate = 1234,
      phoneNumber = "1231231212",
      identificationNumber = "123456789",
      identificationType = "cc",
      address = "",
      city = "",
      department = "",
      email = "",
      user = "",
      password = ""
    )

  val scenarioOk =
    scenario("get_user_by_id_ok")
    .exec(
      createUser( user, List.empty )
        .check(status.is(200))
    )

  setUp(
    scenarioOk.inject( atOnceUsers(1) )
  ).protocols(httpProtocol.disableWarmUp)
}