organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.0-SNAPSHOT"

addSparkDeps
publishTestJar

deps ++= Seq(
  libs.value('adam_core),
  libs.value('bdg_formats),
  libs.value('genomic_utils),
  libs.value('htsjdk),
  libs.value('loci),
  libs.value('reference),
  libs.value('scalautils),
  libs.value('slf4j)
)

