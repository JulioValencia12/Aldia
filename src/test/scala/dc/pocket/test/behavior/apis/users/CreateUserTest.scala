package dc.pocket.test.behavior.apis.users

import scala.util.Random
import dc.pocket.test.Environment
import dc.pocket.test.apis.UserAPI
import dc.pocket.test.domain.{Credentials, User}
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CreateUserTest extends Simulation with UserAPI with Environment {

  val rnd = new Random()

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  val user: User =
    User(
      firstName = ("Test" +randomString(6) ),
      secondName = ("" +randomString(6) ),
      lastName = ("" +randomString(9) ),
      birthDate = 1991,
      phoneNumber = "1231231212",
      identificationNumber = ("" +randomString(6)),
      identificationType = "cc",
      address = ("" +randomString(11) ),
      city = ("" +randomString(9) ),
      department = ("" +randomString(9) ),
      email = ("test" + randomString(7) + "@gmail.com"),
      credentials = Credentials( user = ("test" +randomString(8) ), password = ("" +randomString(9) ))
    )

//  val user2: User =
//    User(
//      firstName = "",
//      secondName = "",
//      lastName = "",
//      birthDate = ,
//      phoneNumber = "",
//      identificationNumber = "",
//      identificationType = "",
//      address = "",
//      city = "",
//      department = "",
//      email = "",
//      user = "",
//      password = ""
//    )

  val scenarioOk =
    scenario("create_user_ok")
    .exec(
      createUser( user, List.empty )
        .check(status.is(200))
    )

//  val scenarioKo =
//    scenario("create_user_ko")
//      .exec(
//        createUser( user2, List.empty )
//          .check(status.is(200))
//      )

  setUp(
    scenarioOk.inject( atOnceUsers(1) ) ,
      //scenarioKo.inject( atOnceUsers(1) )
  ).protocols(httpProtocol.disableWarmUp)
}