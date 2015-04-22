package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class ContentType (name: String,
                   description: String

                    )

object ContentType {
  implicit val contentTypeFmt = Json.format[ContentType]

}
