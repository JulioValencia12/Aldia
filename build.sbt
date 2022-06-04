import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dc.pocket"
ThisBuild / organizationName := "test"

lazy val root = (project in file("."))
  .settings(
    name := "pocket-test",
    libraryDependencies += scalaTest % Test
  )

val gatlingVersion = "3.7.6"


libraryDependencies ++= Seq(  
  "io.gatling.highcharts"   % "gatling-charts-highcharts"     % gatlingVersion % "test"
  ,"io.gatling"             % "gatling-test-framework"        % gatlingVersion % "test"
  ,"io.gatling"             % "gatling-core"                  % gatlingVersion
  , "io.gatling"            % "gatling-http"                  % gatlingVersion

)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


enablePlugins(GatlingPlugin)

