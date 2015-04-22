package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{Category, Address}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//name: String,
//description: String
class CategoryRepository extends CassandraTable[CategoryRepository, Category] {

  object name extends StringColumn(this) with PartitionKey[String]

  object description extends StringColumn(this)

  override def fromRow(row: Row): Category = {
    Category(
      name(row),
      description(row)
    )
  }
}

object CategoryRepository extends CategoryRepository with DataConnection {
  override lazy val tableName = "category"

  def save(category: Category): Future[ResultSet] = {
    insert
      .value(_.name, category.name)
      .value(_.description, category.description)
      .future()
  }

  def getCategoryByName(name: String): Future[Option[Category]] = {
    select.where(_.name eqs name).one()
  }

  def getAllCategories: Future[Seq[Category]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}


