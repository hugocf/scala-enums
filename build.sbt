name := "scala-enums"
version := "1.0"

scalaVersion := "2.12.2"
scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.1" % Test withSources())
