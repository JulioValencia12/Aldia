//package dc.pocket.test
//
//import dc.pocket.test.apis.UserAPI
//
//import scala.concurrent.duration._
//import io.gatling.core.Predef._
//import io.gatling.http.Predef._
//import io.gatling.http.request.builder.HttpRequestBuilder
//
//class Example  extends Simulation with UserAPI with Environment {
//
//  val scn = scenario("BasicSimulation")
//    .exec(
//      getUserById("988b6140-ddf5-11ec-bfb6-4984ba70d27f", dataChecks ::: extractData)
//    )
//    .exec(session => {
//      val response = session(userByIdResponseKey).as[String]
//      val firstName = session(userByIdResponseFirstNameKey).as[String]
//      println(s"Response body: \n$response")
//      println(s"Response firs name: $firstName")
//      session
//    })
//    .pause(5)
//
//  setUp(
//    scn.inject(atOnceUsers(1))
//  ).protocols(httpProtocol.disableWarmUp)
//}