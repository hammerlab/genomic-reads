package org.hammerlab.genomics.reads

import com.esotericsoftware.kryo.io.{ Input, Output }
import com.esotericsoftware.kryo.{ Kryo, Serializer }
import org.hammerlab.genomics.bases.Bases

// Serialization: UnmappedRead
class UnmappedReadSerializer extends Serializer[UnmappedRead] {
  def write(kryo: Kryo, output: Output, obj: UnmappedRead) = {
    output.writeString(obj.name)
    assert(obj.sequence.length == obj.baseQualities.length)
    kryo.writeClassAndObject(output, obj.sequence)
    output.writeBytes(obj.baseQualities.toArray)
    output.writeBoolean(obj.isDuplicate)
    output.writeBoolean(obj.failedVendorQualityChecks)
    output.writeBoolean(obj.isPaired)

  }

  def read(kryo: Kryo, input: Input, klass: Class[UnmappedRead]): UnmappedRead = {
    val name: String = input.readString()
    val sequence: Bases = kryo.readClassAndObject(input).asInstanceOf[Bases]
    val qualityScoresArray = input.readBytes(sequence.length).toVector
    val isDuplicate = input.readBoolean()
    val failedVendorQualityChecks = input.readBoolean()
    val isPaired = input.readBoolean()

    UnmappedRead(
      name,
      sequence,
      qualityScoresArray,
      isDuplicate,
      failedVendorQualityChecks,
      isPaired
    )
  }
}

