package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Role(
           roleName: String,
           description: String
            )

object Role {
  implicit val roleFmt = Json.format[Role]

}