package conf.connections

/**
 * Created by hashcode on 2015/04/16.
 */
import com.datastax.driver.core.{Session, Cluster}
import com.websudos.phantom.Implicits._

import scala.concurrent._

/**
 * Created by hashcode on 2014/12/01.
 */
object DataConnection {
  val keySpace = "infoshare"

  lazy val cluster = Cluster.builder()
    .addContactPoints("localhost")
    .withPort(9042)
    .withoutJMXReporting()
    .withoutMetrics()
    .build()

  lazy val session = blocking {
    cluster.connect(keySpace)
  }
}

trait DataConnection {
  self: CassandraTable[_, _] =>

  def createTable(): Future[Unit] = {
    create.future() map (_ => ())
  }

  implicit lazy val datastax: Session = DataConnection.session
}