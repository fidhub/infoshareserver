package controllers

import _root_.respository.CustomFeedRepository
import _root_.respository.CustomLinkRepository
import _root_.respository.CustomProcessedLinkskRepository
import _root_.respository.ErrorReportRespository
import _root_.respository.FeedsRespository
import _root_.respository.LinksRespository
import _root_.respository.OpenFeedRepository
import _root_.respository.PostRespository
import _root_.respository.PublishedLinksRepository
import _root_.respository.SinglePostRepository
import _root_.respository.SitePostRespository
import _root_.respository.SiteRepository
import _root_.respository.SocialRepository
import _root_.respository.StatsRepository
import _root_.respository.WebSiteRepository
import _root_.respository.ZonePostRespository
import _root_.respository.ZoneRespository
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import repository._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def options(path: String) = Action {
    Ok("")
  }

  def dbsetup = Action.async {
    val results = for {
      address <- AddressRepository.createTable()
      cat <- CategoryRepository.createTable()
      contact <- ContactRepository.createTable()
      content <- ContentRepository.createTable()
      contentType <- ContentTypeRepository.createTable()
      role <- RoleRepository.createTable()
      user <- UserRepository.createTable()
    } yield (user)
    results map (result => {
      Ok(Json.toJson("Done"))
    })
  }

}