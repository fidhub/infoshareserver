package conf.util

import java.util.Date


import org.joda.time.{DateTimeConstants, DateTime}

import scala.util.{Success, Try, Failure}

/**
 * Created by hashcode on 2015/04/16.
 */
object Util extends Enumeration {

  import java.net._
  def md5Hash(text: String): String = {
    val hash = text + InetAddress.getLocalHost.getHostName
    java.security.MessageDigest.getInstance("MD5").digest(hash.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }

  val SPAM, PENDING, APPROVED, QUESTION, RESPONSE, SMFEED,FEED, POST, ENABLED, DISABLED, TODAY,YESTERDAY,WEEK,MONTH = Value

  def getDate(date: String): Date = {
    date match{
      case "TODAY" =>  DateTime.now.withTimeAtStartOfDay().toLocalDate.toDate
      case "YESTERDAY"=>  DateTime.now.minusDays(1).toDate
      case "WEEK" =>  DateTime.now.withTimeAtStartOfDay.withDayOfWeek(DateTimeConstants.SUNDAY).minusDays(7).toDate
      case "MONTH" =>  DateTime.now.withTimeAtStartOfDay.dayOfMonth.withMinimumValue.toDate
      case _ => DateTime.now.toDate
    }
  }

  def getDateFromString(date:String):Date ={
    DateTime.parse(date).toDate
  }

  def yearDate = {
    DateTime.now.dayOfYear().withMinimumValue().withTimeAtStartOfDay().toDate
  }
  def getIntFromString(value:String):Int ={
    Try(Integer.parseInt(value)) match {
      case Success(ans) => ans
      case Failure(ex) => 0
    }
  }



  def getMedecription(article: String) = {
    if (article.length > 156) {
      val description = article.substring(0, 156)
      description.split(' ').map(_.capitalize).mkString(" ")
    } else {
      article
    }
  }



  def getCaption() = {
    "NoCaption"
  }

  def getMovies = {

  }
}
