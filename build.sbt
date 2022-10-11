ThisBuild / scalaVersion := "2.13.3"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """cubedataapi""",


    libraryDependencies ++= Seq(
      // Enable reactive mongo for Play 2.8
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.reactivemongo" %% "play2-reactivemongo" % "0.20.13-play28",
      // Provide JSON serialization for reactive mongo
      "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.0.1-play28",
      // Provide BSON serialization for reactive mongo
      "org.reactivemongo" %% "reactivemongo-bson-compat" % "0.20.13",
      // Provide JSON serialization for Joda-Time
      "com.typesafe.play" %% "play-json-joda" % "2.7.4",
      "com.github.jwt-scala" %% "jwt-core" % "7.1.3",
      /*"org.scalatest" %% "scalatest" % "3.2.9" % Test,*/
      /*"org.scalamock" %% "scalamock" % "5.1.0" % Test,*/
/*      "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.26",
      "org.apache.commons" % "commons-text" % "1.10.0",
      "org.apache.commons" % "commons-lang3" % "3.12.0",*/
      "org.jsoup" % "jsoup" % "1.15.3"
)

    /*libraryDependencies ++= Seq(
      guice,
      // Enable reactive mongo for Play 2.8
      "org.reactivemongo" %% "play2-reactivemongo" % "0.20.13-play28",
      // Provide JSON serialization for reactive mongo
      "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.0.1-play28",
      // Provide BSON serialization for reactive mongo
      "org.reactivemongo" %% "reactivemongo-bson-compat" % "0.20.13",
      // Provide JSON serialization for Joda-Time
      "com.typesafe.play" %% "play-json-joda" % "2.7.4",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.5",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
    )*/

  )

