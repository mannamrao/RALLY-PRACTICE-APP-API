lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """RALLY-PRACTICE-APP-API""",
    version := "2.8.x",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
      "com.github.t3hnar" %% "scala-bcrypt" % "4.3.0",
      specs2 % Test,
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
