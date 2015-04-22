package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.{DateColumn, PrimitiveColumn}
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{Address, Content}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */

//id: String,
//postalAddress: String,
//physicalAddress: String,
//postalCode: String,
//addressType: String
class AddressRepository extends CassandraTable[AddressRepository, Address] {

  object id extends StringColumn(this) with PartitionKey[String]

  object postalAddress extends StringColumn(this)

  object physicalAddress extends StringColumn(this)

  object postalCode extends StringColumn(this)

  object addressType extends StringColumn(this)


  override def fromRow(row: Row): Address = {
    Address(
      id(row),
      postalAddress(row),
      physicalAddress(row),
      postalCode(row),
      addressType(row)
    )
  }
}

object AddressRepository extends AddressRepository with DataConnection {
  override lazy val tableName = "address"

  def save(address: Address): Future[ResultSet] = {
    insert
      .value(_.id, address.id)
      .value(_.physicalAddress, address.physicalAddress)
      .value(_.postalAddress, address.postalAddress)
      .value(_.postalCode, address.postalCode)
      .value(_.addressType, address.addressType)

      .future()
  }

  def getAddressId(id: String): Future[Option[Address]] = {
    select.where(_.id eqs id).one()
  }

  def getAllAddresses: Future[Seq[Address]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }


}