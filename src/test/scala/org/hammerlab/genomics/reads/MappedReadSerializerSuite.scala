package org.hammerlab.genomics.reads

import htsjdk.samtools.TextCigarCodec

class MappedReadSerializerSuite
  extends ReadSerializerSuite {

  kryoRegister(classOf[MappedRead], new MappedReadSerializer)

  test("mapped read") {
    val read = MappedRead(
      "read1",
      "TCGACCCTCGA",
      Array[Byte]((10 to 20).map(_.toByte): _*),
      isDuplicate = true,
      "chr5",
      50,
      325352323,
      TextCigarCodec.decode(""),
      failedVendorQualityChecks = false,
      isPositiveStrand = true,
      isPaired = true
    )

    val serialized = serialize(read)
    val deserialized = deserialize[MappedRead](serialized)

    // We *should* be able to just use MappedRead's equality implementation, since Scala should implement the equals
    // method for case classes. Somehow, something goes wrong though, and this fails:

    // deserialized should equal(read)

    // So, instead, we'll compare each field ourselves:
    deserialized.name should equal(read.name)
    deserialized.sequence should equal(read.sequence)
    deserialized.baseQualities should equal(read.baseQualities)
    deserialized.isDuplicate should equal(read.isDuplicate)
    deserialized.contigName should equal(read.contigName)
    deserialized.alignmentQuality should equal(read.alignmentQuality)
    deserialized.start should equal(read.start)
    deserialized.cigar should equal(read.cigar)
    deserialized.failedVendorQualityChecks should equal(read.failedVendorQualityChecks)
    deserialized.isPositiveStrand should equal(read.isPositiveStrand)
    deserialized.isPaired should equal(read.isPaired)
  }

  test("mapped read with mdtag") {
    val read = MappedRead(
      "read1",
      "TCGACCCTCGA",
      Array[Byte]((10 to 20).map(_.toByte): _*),
      isDuplicate = true,
      "chr5",
      50,
      325352323,
      TextCigarCodec.decode(""),
      failedVendorQualityChecks = false,
      isPositiveStrand = true,
      isPaired = true
    )

    val serialized = serialize(read)
    val deserialized = deserialize[MappedRead](serialized)

    // We *should* be able to just use MappedRead's equality implementation, since Scala should implement the equals
    // method for case classes. Somehow, something goes wrong though, and this fails:

    // deserialized should equal(read)

    // So, instead, we'll compare each field ourselves:
    deserialized.name should equal(read.name)
    deserialized.sequence should equal(read.sequence)
    deserialized.baseQualities should equal(read.baseQualities)
    deserialized.isDuplicate should equal(read.isDuplicate)
    deserialized.contigName should equal(read.contigName)
    deserialized.alignmentQuality should equal(read.alignmentQuality)
    deserialized.start should equal(read.start)
    deserialized.cigar should equal(read.cigar)
    deserialized.failedVendorQualityChecks should equal(read.failedVendorQualityChecks)
    deserialized.isPositiveStrand should equal(read.isPositiveStrand)
    deserialized.isPaired should equal(read.isPaired)
  }

  test("mapped read with unmapped pair") {
    val read = MappedRead(
      "read1",
      "TCGACCCTCGA",
      Array[Byte]((10 to 20).map(_.toByte): _*),
      isDuplicate = true,
      "chr5",
      50,
      325352323,
      TextCigarCodec.decode(""),
      failedVendorQualityChecks = false,
      isPositiveStrand = true,
      isPaired = true
    )

    val serialized = serialize(read)
    val deserialized = deserialize[MappedRead](serialized)

    // We *should* be able to just use MappedRead's equality implementation, since Scala should implement the equals
    // method for case classes. Somehow, something goes wrong though, and this fails:

    // deserialized should equal(read)

    // So, instead, we'll compare each field ourselves:
    deserialized.name should equal(read.name)
    deserialized.sequence should equal(read.sequence)
    deserialized.baseQualities should equal(read.baseQualities)
    deserialized.isDuplicate should equal(read.isDuplicate)
    deserialized.contigName should equal(read.contigName)
    deserialized.alignmentQuality should equal(read.alignmentQuality)
    deserialized.start should equal(read.start)
    deserialized.cigar should equal(read.cigar)
    deserialized.failedVendorQualityChecks should equal(read.failedVendorQualityChecks)
    deserialized.isPositiveStrand should equal(read.isPositiveStrand)
    deserialized.isPaired should equal(read.isPaired)
  }

}
