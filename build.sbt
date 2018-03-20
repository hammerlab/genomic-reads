subgroup("genomics", "reads")
v"1.0.7"
github.repo("genomic-reads")
addSparkDeps
publishTestJar

import genomics.{ reference, utils }
dep(
         adam % "0.23.2",
  bdg_formats,
       htsjdk,
    reference % "1.4.3" +testtest,
   scalautils,
        slf4j,
        utils % "1.3.1" +testtest
)
