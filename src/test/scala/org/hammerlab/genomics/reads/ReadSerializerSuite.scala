package org.hammerlab.genomics.reads

import org.hammerlab.spark.test.suite.{ KryoSerializerSuite, SparkSerializerSuite }

class ReadSerializerSuite
  extends KryoSerializerSuite(classOf[Registrar])
    with SparkSerializerSuite
