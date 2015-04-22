package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Category(name: String,
                    description: String
                     )

object Category {
  implicit val categoryFmt = Json.format[Category]
}
