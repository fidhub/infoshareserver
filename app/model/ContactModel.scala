package model

import java.util.UUID

import conf.util.Util
import domain.{Contact, Address}
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */

//id: String,
//phone: String,
//email: String,
//contactType: String
case class ContactModel (phone: String,
                    email: String,
                    contactType: String
                     )

object ContactModel {
  implicit val contactFmt = Json.format[ContactModel]

  def domain(model: ContactModel) = {
    Contact(
      Util.md5Hash(UUID.randomUUID().toString()),
      model.phone,
      model.email,
      model.contactType
    )
  }

}