ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.7.3"

lazy val root = (project in file("."))
  .settings(
    name := "ex1",
    idePackagePrefix := Some("org.func.ex1"), 
    libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.7"
  )