package model

import java.util.UUID

import conf.util.Util
import domain.{Address}
import play.api.libs.json.Json


/**
 * Created by hashcode on 2015/04/16.
 */
case class AddressModel(
                         postalAddress: String,
                         physicalAddress: String,
                         postalCode: String,
                         addressType: String
                         ){
  def getDomain:Address = AddressModel.domain(this)
}



object AddressModel {
  implicit val addressFmt = Json.format[AddressModel]

  def domain(model: AddressModel) = {
    Address(
      Util.md5Hash(UUID.randomUUID().toString()),
      model.postalAddress,
      model.physicalAddress,
      model.postalCode,
      model.addressType
    )
  }

}