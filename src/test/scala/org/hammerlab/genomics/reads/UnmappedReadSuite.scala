package org.hammerlab.genomics.reads

import org.hammerlab.genomics.bases.BasesUtil
import org.hammerlab.test.Suite

class UnmappedReadSuite
  extends Suite
    with BasesUtil {

  test("unmappedread is not mapped") {
    val read =
      UnmappedRead(
        "read1",
        "TCGACCCTCGA",
        Array[Byte]((10 to 20).map(_.toByte): _*),
        isDuplicate = true,
        failedVendorQualityChecks = false,
        isPaired = false
      )

    read.isMapped should be(false)
    read.asInstanceOf[Read].isMapped should be(false)

    val collectionMappedReads: Seq[Read] = Seq(read)
    collectionMappedReads(0).isMapped should be(false)
  }
}
