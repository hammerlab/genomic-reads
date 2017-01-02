package org.hammerlab.genomics.reads

import org.hammerlab.spark.test.suite.{ KryoSparkSuite, SparkSerialization }

class ReadSerializerSuite
  extends KryoSparkSuite(classOf[Registrar])
    with SparkSerialization
