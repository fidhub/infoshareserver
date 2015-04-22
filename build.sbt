name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "com.websudos" % "phantom-dsl_2.11" % "1.5.0"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.4"

libraryDependencies += "joda-time" % "joda-time" % "2.5"

libraryDependencies += "org.scala-lang.modules" % "scala-async_2.10" % "0.9.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"