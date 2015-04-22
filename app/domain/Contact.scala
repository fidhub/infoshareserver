package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Contact (id: String,
               phone: String,
               email: String,
               contactType: String
                )

object Contact {
  implicit val contactFmt = Json.format[Contact]

}