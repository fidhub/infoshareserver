package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{ContentType, Category}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//name: String,
//description: String
class ContentTypeRepository extends CassandraTable[ContentTypeRepository, ContentType] {

  object name extends StringColumn(this) with PartitionKey[String]

  object description extends StringColumn(this)

  override def fromRow(row: Row): Category = {
    Category(
      name(row),
      description(row)
    )
  }
}

object ContentTypeRepository extends ContentTypeRepository with DataConnection {
  override lazy val tableName = "contypes"

  def save(contentType: ContentType): Future[ResultSet] = {
    insert
      .value(_.name, contentType.name)
      .value(_.description, contentType.description)
      .future()
  }

  def getContentTypeByName(name: String): Future[Option[ContentType]] = {
    select.where(_.name eqs name).one()
  }

  def getAllContentTypes: Future[Seq[ContentType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

