subgroup("genomics", "reads")
v"1.0.7"
github.repo("genomic-reads")
spark
publishTestJar

import genomics.{ reference, utils }
dep(
         adam % "0.23.4",
  bdg.formats,
       htsjdk,
    reference % "1.5.0" +testtest,
   scalautils,
        slf4j,
        utils % "1.3.2" +testtest
)
