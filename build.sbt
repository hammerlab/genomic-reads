organization := "org.hammerlab.genomics"
name := "reads"
version := "1.0.0-SNAPSHOT"

libraryDependencies ++= Seq(
  libraries.value('adam_core),
  libraries.value('bdg_formats),
  libraries.value('genomic_utils),
  libraries.value('htsjdk),
  libraries.value('kryo),
  libraries.value('loci),
  libraries.value('slf4j)
)

providedDeps += libraries.value('spark)

testDeps ++= Seq(
  libraries.value('spark_tests),
  libraries.value('test_utils)
)
