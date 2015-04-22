package repository

import java.util.Date


import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PrimaryKey
import conf.connections.DataConnection

import domain.{Content}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
class ContentRepository extends CassandraTable[ContentRepository, Content] {

  object id extends StringColumn(this) with PartitionKey[String]

  object category extends StringColumn(this)

  object creator extends StringColumn(this)

  object dateCreated extends DateColumn(this)

  object source extends StringColumn(this)

  object title extends StringColumn(this)

  object content extends StringColumn(this)

  object contentType extends StringColumn(this)


  override def fromRow(row: Row): Content = {
    Content(
      id(row),
      dateCreated(row),
      creator(row),
      source(row),
      category(row),
      title(row),
      content(row),
      contentType(row)
    )
  }
}

object ContentRepository extends ContentRepository with DataConnection {
  override lazy val tableName = "content"

  def save(content: Content): Future[ResultSet] = {
    insert
      .value(_.id, content.id)
      .value(_.category, content.id)
      .value(_.content, content.content)
      .value(_.contentType, content.contentType)
      .value(_.creator, content.creator)
      .value(_.dateCreated, content.dateCreated)
      .value(_.source, content.source)
      .value(_.title, content.title)
      .future()
  }

  def getContentById(id: String): Future[Option[Content]] = {
    select.where(_.id eqs id).one()
  }

  def getAllContents: Future[Seq[Content]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }


}