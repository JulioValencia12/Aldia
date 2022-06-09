package dc.pocket.test.apis

import dc.pocket.test.domain.User
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.request.builder.HttpRequestBuilder
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

trait UserAPI {
  type UserID = String

  val path = "/v1/user"

  val userByIdResponseKey  = "GET_USER_BY_ID_RESPONSE"

  val userByIdResponseFirstNameKey            = "GET_USER_BY_ID_RESPONSE_FIRST_NAME"
  val userByIdResponseLastNameKey             = "GET_USER_BY_ID_RESPONSE_LAST_NAME"
  val userByIdResponseBirthDateKey            = "GET_USER_BY_ID_RESPONSE_BIRT_DATE"
  val userByIdResponsePhoneNumberKey          = "GET_USER_BY_ID_RESPONSE_PHONE_NUMBER"
  val userByIdResponseIdentificationNumberKey = "GET_USER_BY_ID_RESPONSE_IDENTIFICATION_NUMBER"
  val userByIdResponseAddressKey              = "GET_USER_BY_ID_RESPONSE_ADDRESS"
  val userByIdResponseCityKey                 = "GET_USER_BY_ID_RESPONSE_CITY"
  val userByIdResponseDepartmentKey           = "GET_USER_BY_ID_RESPONSE_DEPARTMENT"
  val userByIdResponseUserConfigKey           = "GET_USER_BY_ID_RESPONSE_USER_CONFIG"
  val userByIdResponseEmailKey                = "GET_USER_BY_ID_RESPONSE_EMAIL"
  val userByIdResponseCredentialsKey          = "GET_USER_BY_ID_RESPONSE_CREDENTIALS"

  val dataChecks: List[HttpCheck] = List(
    status.is(session => 200),
    jsonPath("$.firstName").notNull,
    jsonPath("$.lastName").notNull,
    jsonPath("$.birthDate").notNull,
    jsonPath("$.phoneNumber").notNull,
    jsonPath("$.identificationNumber").notNull,
    jsonPath("$.address").notNull,
    jsonPath("$.city").notNull,
    jsonPath("$.department").notNull,
    jsonPath("$.userConfig").exists,
    jsonPath("$.email").notNull,
    jsonPath("$.credentials").exists,
  )

  val extractData: List[HttpCheck] = List(
    bodyString.saveAs(userByIdResponseKey),
    jsonPath("$.firstName").saveAs(userByIdResponseFirstNameKey),
    jsonPath("$.lastName").saveAs(userByIdResponseLastNameKey),
    jsonPath("$.birthDate").saveAs(userByIdResponseBirthDateKey),
    jsonPath("$.phoneNumber").saveAs(userByIdResponsePhoneNumberKey),
    jsonPath("$.identificationNumber").saveAs(userByIdResponseIdentificationNumberKey),
    jsonPath("$.address").saveAs(userByIdResponseAddressKey),
    jsonPath("$.city").saveAs(userByIdResponseCityKey),
    jsonPath("$.department").saveAs(userByIdResponseDepartmentKey),
    jsonPath("$.userConfig").saveAs(userByIdResponseUserConfigKey),
    jsonPath("$.email").saveAs(userByIdResponseEmailKey),
    jsonPath("$.credentials").saveAs(userByIdResponseCredentialsKey)
  )

  val getUserById: (UserID, List[HttpCheck]) => HttpRequestBuilder = (userId, checks) =>
    http("get_user_by_id")
      .get(path)
      .queryParam("userId", userId)
      .check(checks: _*)

  val createUser: (User, List[HttpCheck]) => HttpRequestBuilder = (user, checks) => {
    println(user.asJson.noSpaces)
    http("create_user")
      .post(s"$path/create")
      .body(StringBody(user.asJson.noSpaces))
      .check(checks: _*)
  }


}
