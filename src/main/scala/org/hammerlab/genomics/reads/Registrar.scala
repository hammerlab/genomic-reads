package org.hammerlab.genomics.reads

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator
import org.hammerlab.genomics.bases

class Registrar extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    new bases.Registrar().registerClasses(kryo)

    kryo.register(classOf[MappedRead], new MappedReadSerializer)
    kryo.register(classOf[UnmappedRead], new UnmappedReadSerializer)
  }
}
