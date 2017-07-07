organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.6-SNAPSHOT"

addSparkDeps
publishTestJar

deps ++= Seq(
  libs.value('adam_core).copy(revision = "0.23.2-SNAPSHOT"),
  libs.value('bdg_formats),
  libs.value('htsjdk),
  libs.value('scalautils),
  libs.value('slf4j)
)

testUtilsVersion := "1.2.4-SNAPSHOT"

compileAndTestDeps ++= Seq(
  libs.value('genomic_utils),
  libs.value('reference)
)
