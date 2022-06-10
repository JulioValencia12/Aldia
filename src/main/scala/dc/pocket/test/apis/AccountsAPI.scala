package dc.pocket.test.apis

import dc.pocket.test.domain.{Pocket, User}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.request.builder.HttpRequestBuilder
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

trait AccountsAPI {
  type UserID = String
  type AccountNumber = String

  val path = "/v1/account"

  val accountByIdResponseKey       = "GET_ACCOUNT_BY_ID_RESPONSE"
  val balanceByAccountResponseKey  = "GET_BALANCE_BY_ACCOUNT_RESPONSE"
  val pocketByIdResponseKey        = "GET_POCKET_BY_ID_RESPONSE"

  val accountByIdResponseAccountNumberKey            = "GET_ACCOUNT_BY_ID_RESPONSE_ACCOUNT_NUMBER"
  val balanceByAccountResponseBalanceKey             = "GET_BALANCE_BY_ACCOUNT_RESPONSE_BALANCE"
  val balanceByAccountResponseCurrencyKey            = "GET_BALANCE_BY_ACCOUNT_RESPONSE_CURRENCY"

  val pocketByIdResponseNumberKey                    = "GET_POCKET_BY_ID_RESPONSE_NUMBER"
  val pocketByIdResponseNameKey                      = "GET_POCKET_BY_ID_RESPONSE_NAME"
  val pocketByIdResponseDescriptionKey               = "GET_POCKET_BY_ID_RESPONSE_DESCRIPTION"

// ******************************* datachecks ***************************************

  val dataChecksAccountById: List[HttpCheck] = List(
    status.is(session => 200),
    jsonPath("$.accountNumber").notNull
  )

  val dataChecksPocketById: List[HttpCheck] = List(
    status.is(session => 200),
    jsonPath("$.pockets[0]").exists,
    jsonPath("$.pockets[1]").exists,
    jsonPath("$.pockets[0].number").notNull,
    jsonPath("$.pockets[1].number").notNull,
    jsonPath("$.pockets[0].name").notNull,
    jsonPath("$.pockets[1].name").notNull,
    jsonPath("$.pockets[0].description").notNull,
    jsonPath("$.pockets[1].description").notNull,
  )

  val dataChecksBalance: List[HttpCheck] = List(
    status.is(session => 200),
    jsonPath("$.balance").exists,
    jsonPath("$.currency").notNull
  )

// ******************************* ExtractData ****************************

  val extractDataAccount: List[HttpCheck] = List(
    bodyString.saveAs(accountByIdResponseKey),
    jsonPath("$.accountNumber").saveAs(accountByIdResponseAccountNumberKey),
  )

  val extractDataBalance: List[HttpCheck] = List(
    bodyString.saveAs(balanceByAccountResponseKey),
    jsonPath("$.balance").saveAs(balanceByAccountResponseBalanceKey),
    jsonPath("$.currency").saveAs(balanceByAccountResponseCurrencyKey)
  )

// ******************************* funciones *******************************

  val getAccountById: (UserID, List[HttpCheck]) => HttpRequestBuilder = (userId, checks) =>
    http("get_account_by_id")
      .get(s"$path/accountNumber")
      .queryParam("userId", userId)
      .check(checks: _*)

  val getPocketById: (UserID, List[HttpCheck]) => HttpRequestBuilder = (userId, checks) =>
    http("get_pocket_by_id")
      .get(s"$path/pockets")
      .queryParam("userId", userId)
      .check(checks: _*)

  val getBalanceByAccount: (AccountNumber, List[HttpCheck]) => HttpRequestBuilder = (accountNumber, checks) =>
    http("get_balance_by_accountNumber")
      .get(s"$path//balance/$accountNumber")
      .check(checks: _*)

  val createPocket: (Pocket, List[HttpCheck]) => HttpRequestBuilder = (pocket, checks) => {
    println(pocket.asJson.noSpaces)
    http("create_pocket")
      .post(s"$path/createPocket")
      .body(StringBody(pocket.asJson.noSpaces))
      .check(checks: _*)
  }

}


//router.get('/movements/:accountNumber/:sinceDate/:toDate', function(request,response,next) {
//getMovements( request.params.accountNumber, request.params.sinceDate, request.params.toDate )
//.then((success) => {
//response.send( success )
//}).catch((error) => {
//response.status(500).send( { error: error } );
//})
//});

