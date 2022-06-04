package dc.pocket.test.domain

final case class User(
                       firstName:String,
                       secondName: String,
                       lastName:String,
                       birthDate:Int,
                       phoneNumber:String,
                       identificationNumber:String,
                       identificationType: String,
                       address:String,
                       city:String,
                       department:String,
                       email:String,
                       user: String,
                       password: String
                     )

case class UserConfig(billPreferences:String, rechargePreferences:String, friendAccounts:String)
