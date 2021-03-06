package org.hammerlab.genomics.reads

import com.esotericsoftware.kryo.io.{ Input, Output }
import com.esotericsoftware.kryo.{ Kryo, Serializer }
import htsjdk.samtools.TextCigarCodec
import org.hammerlab.genomics.bases.Bases
import org.hammerlab.genomics.reference.{ ContigName, Locus }
import org.scalautils.ConversionCheckedTripleEquals._

class MappedReadSerializer extends Serializer[MappedRead] {

  def write(kryo: Kryo, output: Output, obj: MappedRead) = {
    output.writeString(obj.name)
    assert(obj.sequence.length === obj.baseQualities.length)
    kryo.writeObject(output, obj.sequence)
    output.writeBytes(obj.baseQualities.toArray)
    output.writeBoolean(obj.isDuplicate)
    kryo.writeObject(output, obj.contigName)
    output.writeInt(obj.alignmentQuality, true)
    output.writeLong(obj.start.locus, true)
    output.writeString(obj.cigar.toString)
    output.writeBoolean(obj.failedVendorQualityChecks)
    output.writeBoolean(obj.isPositiveStrand)
    output.writeBoolean(obj.isPaired)
  }

  def read(kryo: Kryo, input: Input, klass: Class[MappedRead]): MappedRead = {
    val name = input.readString()
    val sequence = kryo.readObject[Bases](input, classOf[Bases])
    val qualityScoresArray: IndexedSeq[Byte] = input.readBytes(sequence.length).toVector
    val isDuplicate = input.readBoolean()
    val contigName = kryo.readObject(input, classOf[ContigName])
    val alignmentQuality = input.readInt(true)
    val start = input.readLong(true)
    val cigarString = input.readString()
    val failedVendorQualityChecks = input.readBoolean()
    val isPositiveStrand = input.readBoolean()
    val cigar = TextCigarCodec.decode(cigarString)

    val isPaired = input.readBoolean()

    MappedRead(
      name,
      sequence,
      qualityScoresArray,
      isDuplicate,
      contigName,
      alignmentQuality,
      Locus(start),
      cigar,
      failedVendorQualityChecks,
      isPositiveStrand,
      isPaired
    )
  }
}

