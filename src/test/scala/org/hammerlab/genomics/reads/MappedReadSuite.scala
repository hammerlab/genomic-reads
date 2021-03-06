package org.hammerlab.genomics.reads

import htsjdk.samtools.{ SAMFileHeader, SAMRecord, TextCigarCodec }
import org.hammerlab.genomics.bases.BasesUtil
import org.hammerlab.genomics.cigar.makeCigar
import org.hammerlab.genomics.reference.test.LociConversions._
import org.hammerlab.test.Suite

class MappedReadSuite
  extends Suite
    with BasesUtil {

  test("mappedread is mapped") {
    val read =
      MappedRead(
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

    read.isMapped should be(true)
    read.asInstanceOf[Read].isMapped should be(true)
  }

  test("mixed collections mapped and unmapped reads") {
    val uread =
      UnmappedRead(
        "read1",
        "TCGACCCTCGA",
        Array[Byte]((10 to 20).map(_.toByte): _*),
        isDuplicate = true,
        failedVendorQualityChecks = false,
        isPaired = true
      )

    val mread =
      MappedRead(
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

    val collectionMappedReads: Seq[Read] = Seq(uread, mread)
    ==(collectionMappedReads(0).isMapped, false)
    ==(collectionMappedReads(1).isMapped, true)
  }

  test("unclippedStart/End") {
    val header = new SAMFileHeader
    val record = new SAMRecord(header)
    record.setMateUnmappedFlag(false)
    record.setAlignmentStart(123)
    record.setReadBases("AAAAACCCCCGGGGGTTTTT".getBytes)
    record.setBaseQualities("@" * 20 getBytes)
    record.setCigar("5H8M7S")

    val read = Read(record).asMappedRead.get

    ==(read.unclippedStart, 117)
    ==(read.unclippedEnd,   137)
  }
}
