organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.6-SNAPSHOT"

addSparkDeps
publishTestJar

deps ++= Seq(
  adam % "0.23.2",
  bdg_formats,
  htsjdk,
  scalautils,
  slf4j
)

compileAndTestDeps ++= Seq(
  genomic_utils % "1.3.0",
  reference % "1.4.0"
)
