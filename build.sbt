name := "topwords"

version := "0.1"

javacOptions += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
 "com.novocode"   %  "junit-interface" % "0.11"   % Test,
 "org.scalatest"  %% "scalatest"       % "3.0.5"  % Test
)

enablePlugins(JavaAppPackaging)
