package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{Role, Category}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//roleName: String,
//description: String
class RoleRepository extends CassandraTable[RoleRepository, Role] {

  object roleName extends StringColumn(this) with PartitionKey[String]

  object description extends StringColumn(this)

  override def fromRow(row: Row): Category = {
    Category(
      roleName(row),
      description(row)
    )
  }
}

object RoleRepository extends RoleRepository with DataConnection {
  override lazy val tableName = "roles"

  def save(role: Role): Future[ResultSet] = {
    insert
      .value(_.roleName, role.roleName)
      .value(_.description, role.description)
      .future()
  }

  def getContentTypeByName(name: String): Future[Option[Role]] = {
    select.where(_.roleName eqs name).one()
  }

  def getAllContentTypes: Future[Seq[Role]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

