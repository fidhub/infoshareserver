package controllers

import _root_.services.feeds.CustomFeedsService
import controllers.CustomFeedController._
import model.ContentModel
import models.CustomFeedModel
import play.api.libs.json.Json
import play.api.mvc.BodyParsers.parse
import play.api.mvc.{Action, Controller}
import repository.ContentRepository

/**
 * Created by hashcode on 2015/04/17.
 */
object ContentController extends Controller {

  def create(zone: String) = Action.async(parse.json) {

    request =>
      val input = request.body
      val contentModel = Json.fromJson[ContentModel](input).get
      val content = contentModel.getDomain()

      val results = ContentRepository.save(content)
      results.map(result =>
        Ok(Json.toJson(content)))
  }

}
