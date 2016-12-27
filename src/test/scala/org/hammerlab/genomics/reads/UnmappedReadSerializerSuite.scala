package org.hammerlab.genomics.reads

class UnmappedReadSerializerSuite
  extends ReadSerializerSuite {

  test("serialize / deserialize unmapped read") {
    val read =
      UnmappedRead(
        "read1",
        "TCGACCCTCGA",
        (10 to 20).map(_.toByte).toArray,
        isDuplicate = true,
        failedVendorQualityChecks = false,
        isPaired = true
      )

    deserialize[MappedRead](serialize(read)) should equal(read)
  }
}
