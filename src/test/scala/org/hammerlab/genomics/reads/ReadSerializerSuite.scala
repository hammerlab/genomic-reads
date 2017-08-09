package org.hammerlab.genomics.reads

import org.hammerlab.genomics.bases.BasesUtil
import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class ReadSerializerSuite
  extends KryoSparkSuite
    with SparkSerialization
    with BasesUtil {
  register(new Registrar)
}
