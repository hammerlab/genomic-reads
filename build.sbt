organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.6-SNAPSHOT"

addSparkDeps
publishTestJar

deps ++= Seq(
  adam % "0.23.2-SNAPSHOT",
  bdg_formats,
  htsjdk,
  scalautils,
  slf4j
)

testUtilsVersion := "1.2.4-SNAPSHOT"

compileAndTestDeps ++= Seq(
  genomic_utils % "1.3.0-SNAPSHOT",
  reference % "1.4.0-SNAPSHOT"
)
