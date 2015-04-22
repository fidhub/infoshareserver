package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.{DateColumn, PrimitiveColumn}
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection
import domain.{User, Content}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//otherName: String,
//firstName: String,
//lastName: String,
//username: String,
//password: String,
//role: List[String],
//contact: List[String],
//address: List[String]
class UserRepository extends CassandraTable[UserRepository, User] {

  object username extends StringColumn(this) with PartitionKey[String]

  object firstName extends StringColumn(this)

  object lastName extends StringColumn(this)

  object otherName extends StringColumn(this)

  object password extends StringColumn(this)

  object role extends ListColumn(this)

  object contact extends ListColumn(this)

  object address extends ListColumn(this)


  override def fromRow(row: Row): User = {
    User(
      username(row),
      firstName(row),
      lastName(row),
      otherName(row),
      password(row),
      role(row),
      contact(row),
      address(row)
    )
  }
}

object UserRepository extends UserRepository with DataConnection {
  override lazy val tableName = "users"

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.address, user.address)
      .value(_.contact, user.contact)
      .value(_.firstName, user.firstName)
      .value(_.lastName, user.lastName)
      .value(_.otherName, user.otherName)
      .value(_.password, user.password)
      .value(_.role, user.role)
      .value(_.username, user.username)
      .future()
  }

  def getUserByUsername(username: String): Future[Option[User]] = {
    select.where(_.username eqs username).one()
  }

  def getAllUsers: Future[Seq[User]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }


}