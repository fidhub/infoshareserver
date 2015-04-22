package model

import java.util.{UUID, Date}


import conf.util.Util
import domain.{Content}
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class ContentModel (
                    dateCreated: Date,
                    creator: String,
                    source: String,
                    category: String,
                    title: String,
                    content:String,
                    contentType:String
                     ){
  def getDomain():Content=ContentModel.domain(this)
}
object ContentModel {
  implicit val contentFmt = Json.format[ContentModel]

  def domain(model: ContentModel) = {
    Content(
      Util.md5Hash(UUID.randomUUID().toString()),
      model.dateCreated,
      model.creator,
      model.source,
      model.category,
      model.title,
      model.content,
      model.contentType)
  }
}