package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class User(otherName: String,
           firstName: String,
           lastName: String,
           username: String,
           password: String,
           role: List[String],
           contact: List[String],
           address: List[String]
            )

object User {
  implicit val userFmt = Json.format[User]

}