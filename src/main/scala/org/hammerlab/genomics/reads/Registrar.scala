package org.hammerlab.genomics.reads

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator
import org.hammerlab.genomics.{ bases, reference }

class Registrar extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    new bases.Registrar().registerClasses(kryo)
    new reference.Registrar().registerClasses(kryo)

    kryo.register(classOf[MappedRead], new MappedReadSerializer)
    kryo.register(classOf[Array[MappedRead]])

    kryo.register(classOf[UnmappedRead], new UnmappedReadSerializer)
    kryo.register(classOf[Array[UnmappedRead]])

    kryo.register(classOf[PairedRead[_]])
    kryo.register(classOf[MateAlignmentProperties])
    kryo.register(classOf[Array[PairedRead[_]]])

    kryo.register(classOf[Array[Read]])
  }
}
