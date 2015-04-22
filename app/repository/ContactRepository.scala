package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{Contact, Address}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//id: String,
//phone: String,
//email: String,
//contactType: String
class ContactRepository extends CassandraTable[ContactRepository, Contact] {

  object id extends StringColumn(this) with PartitionKey[String]

  object phone extends StringColumn(this)

  object email extends StringColumn(this)

  object contactType extends StringColumn(this)

  override def fromRow(row: Row): Contact = {
    Contact(
      id(row),
      phone(row),
      email(row),
      contactType(row)
    )
  }
}

object ContactRepository extends ContactRepository with DataConnection {
  override lazy val tableName = "contacts"

  def save(contact: Contact): Future[ResultSet] = {
    insert
      .value(_.id, contact.id)
      .value(_.contactType, contact.contactType)
      .value(_.email, contact.email)
      .value(_.phone, contact.phone)
      .future()
  }

  def getContactById(id: String): Future[Option[Contact]] = {
    select.where(_.id eqs id).one()
  }

  def getAllContacts: Future[Seq[Contact]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}


