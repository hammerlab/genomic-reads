organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.0"

addSparkDeps
publishTestJar

deps ++= Seq(
  libs.value('adam_core),
  libs.value('bdg_formats),
  libs.value('htsjdk),
  libs.value('scalautils),
  libs.value('slf4j)
)

compileAndTestDeps ++= Seq(
  libs.value('genomic_utils),
  libs.value('reference)
)
