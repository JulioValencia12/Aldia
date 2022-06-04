package dc.pocket.test.domain

final case class User(
                       firstName:String,
                       lastName:String,
                       birthDate:Int,
                       phoneNumber:String,
                       identificationNumber:String,
                       address:String,
                       city:String,
                       department:String,
                       userConfig:UserConfig,
                       email:String,
                       credentials: String
                     )

case class UserConfig(billPreferences:String, rechargePreferences:String, friendAccounts:String)
