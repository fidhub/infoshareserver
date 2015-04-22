


import akka.actor.Props
import conf.util.CORSFilter

import play.api.libs.concurrent.Akka
import play.api.{Logger, Application, GlobalSettings}
import play.api.mvc.WithFilters

/**
 * Created by hashcode on 2015/04/16.
 */
object Global extends WithFilters(CORSFilter()) with GlobalSettings {
  override def onStart(app: Application): Unit = {
    super.onStart(app)
  }
}