package org.hammerlab.genomics.reads

import org.hammerlab.spark.test.suite.{ KryoSparkSuite, RDDSerialization }

class ReadSerializerSuite
  extends KryoSparkSuite(classOf[Registrar])
    with RDDSerialization
