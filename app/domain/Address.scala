package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Address(id: String,
                   postalAddress: String,
                   physicalAddress: String,
                   postalCode: String,
                   addressType: String
                    )

object Address  {
  implicit val addressFmt = Json.format[Address]

}