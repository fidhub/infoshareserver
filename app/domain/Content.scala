package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Content (id: String,
               dateCreated: Date,
               creator: String,
               source: String,
               category: String,
               title: String,
               content:String,
               contentType:String
                )

object Content {
  implicit val contentFmt = Json.format[Content]

}