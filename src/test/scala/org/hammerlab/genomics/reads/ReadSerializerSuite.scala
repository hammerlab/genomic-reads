package org.hammerlab.genomics.reads

import org.hammerlab.genomics.bases.BasesUtil
import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class ReadSerializerSuite
  extends KryoSparkSuite(classOf[Registrar])
    with SparkSerialization
    with BasesUtil
