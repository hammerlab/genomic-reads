package org.hammerlab.genomics.reads

import htsjdk.samtools.TextCigarCodec
import org.hammerlab.genomics.bases.BasesUtil
import org.hammerlab.genomics.reference.test.LocusUtil
import org.hammerlab.test.Suite

class PairedReadSuite
  extends Suite
    with BasesUtil
    with LocusUtil {

  test("unmappedread paired read is not mapped") {
    val unmappedRead =
      UnmappedRead(
        "read1",
        "TCGACCCTCGA",
        Array[Byte]((10 to 20).map(_.toByte): _*),
        isDuplicate = true,
        failedVendorQualityChecks = false,
        isPaired = true
      )

    val read =
      PairedRead(
        unmappedRead,
        isFirstInPair = true,
        mateAlignmentProperties = Some(
          MateAlignmentProperties(
            inferredInsertSize = Some(300),
            contigName = "chr5",
            start = 100,
            isPositiveStrand = false
          )
        )
      )

    read.isMapped should be(false)
    read.asInstanceOf[Read].isMapped should be(false)

    val collectionMappedReads: Seq[Read] = Seq(read)
    collectionMappedReads(0).isMapped should be(false)
  }

  test("mixed collections mapped and unmapped read pairs") {
    val uread =
      PairedRead(
        UnmappedRead(
          "read1",
          "TCGACCCTCGA",
          Array[Byte]((10 to 20).map(_.toByte): _*),
          isDuplicate = true,
          failedVendorQualityChecks = false,
          isPaired = true
        ),
        isFirstInPair = true,
        mateAlignmentProperties = Some(
          MateAlignmentProperties(
            inferredInsertSize = Some(300),
            contigName = "chr5",
            start = 100,
            isPositiveStrand = false
          )
        )
      )

    val mread =
      PairedRead(
        MappedRead(
          "read1",
          "TCGACCCTCGA",
          Array[Byte]((10 to 20).map(_.toByte): _*),
          isDuplicate = true,
          contigName = "chr5",
          alignmentQuality = 50,
          start = 325352323,
          cigar = TextCigarCodec.decode(""),
          failedVendorQualityChecks = false,
          isPositiveStrand = true,
          isPaired = true
        ),
        isFirstInPair = true,
        mateAlignmentProperties = Some(
          MateAlignmentProperties(
            inferredInsertSize = Some(300),
            contigName = "chr5",
            start = 100,
            isPositiveStrand = false
          )
        )
      )

    val collectionMappedReads: Seq[Read] = Seq(uread, mread)
    collectionMappedReads(0).isMapped should be(false)
    collectionMappedReads(1).isMapped should be(true)
  }

}
