package org.hammerlab.genomics.reads

import org.hammerlab.genomics.cigar._
import org.hammerlab.genomics.reference.test.LocusUtil._

class MappedReadSerializerSuite
  extends ReadSerializerSuite {

  test("mapped read") {
    val read =
      MappedRead(
        "read1",
        "TCGACCCTCGA",
        Array((10 to 20).map(_.toByte): _*),
        isDuplicate = true,
        "chr5",
        50,
        325352323,
        cigar = "",
        failedVendorQualityChecks = false,
        isPositiveStrand = true,
        isPaired = true
      )

    deserialize[MappedRead](serialize(read)) should equal(read)
  }

  test("mapped read with mdtag") {
    val read =
      MappedRead(
        "read1",
        "TCGACCCTCGA",
        Array((10 to 20).map(_.toByte): _*),
        isDuplicate = true,
        "chr5",
        50,
        325352323,
        cigar = "",
        failedVendorQualityChecks = false,
        isPositiveStrand = true,
        isPaired = true
      )

    deserialize[MappedRead](serialize(read)) should equal(read)
  }

  test("mapped read with unmapped pair") {
    val read = MappedRead(
      "read1",
      "TCGACCCTCGA",
      Array((10 to 20).map(_.toByte): _*),
      isDuplicate = true,
      "chr5",
      50,
      325352323,
      cigar = "",
      failedVendorQualityChecks = false,
      isPositiveStrand = true,
      isPaired = true
    )

    deserialize[MappedRead](serialize(read)) should equal(read)
  }

}
