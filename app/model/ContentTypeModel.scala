package model

import java.util.UUID

import conf.util.Util
import domain.{Content, ContentType}

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class ContentTypeModel(
                       name: String,
                       description:String
                        ){
  def getDomain():ContentType=ContentTypeModel.domain(this)
}

object ContentTypeModel {
  implicit val contentTypeFmt = Json.format[ContentTypeModel]

  def domain(model: ContentTypeModel) = {
    ContentType(
      model.name,
      model.description
    )
  }
}
