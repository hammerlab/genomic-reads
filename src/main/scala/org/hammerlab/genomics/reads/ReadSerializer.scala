//package org.hammerlab.genomics.reads
//
//import com.esotericsoftware.kryo.Kryo
//import com.esotericsoftware.kryo.io.{ Input, Output }
//
//class ReadSerializer {
//  def write(kryo: Kryo, output: Output, obj: Read) = {
//    output.writeString(obj.name)
//    assert(obj.sequence.length == obj.baseQualities.length)
//    output.writeInt(obj.sequence.length, true)
//    output.writeBytes(obj.sequence.toArray)
//    output.writeBytes(obj.baseQualities.toArray)
//    output.writeBoolean(obj.isDuplicate)
//    output.writeBoolean(obj.failedVendorQualityChecks)
//    output.writeBoolean(obj.isPaired)
//
//  }
//
//  def read(kryo: Kryo, input: Input, klass: Class[Read]): Read = {
//    val name: String = input.readString()
//    val count: Int = input.readInt(true)
//    val sequenceArray: Vector[Byte] = input.readBytes(count).toVector
//    val qualityScoresArray = input.readBytes(count).toVector
//    val isDuplicate = input.readBoolean()
//    val failedVendorQualityChecks = input.readBoolean()
//    val isPaired = input.readBoolean()
//
//    Read(
//      name,
//      sequenceArray,
//      qualityScoresArray,
//      isDuplicate,
//      failedVendorQualityChecks,
//      isPaired
//    )
//  }
//}
