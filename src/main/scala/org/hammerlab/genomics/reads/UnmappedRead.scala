package org.hammerlab.genomics.reads

import org.hammerlab.genomics.bases.Bases

/**
 * An unmapped read. See the [[Read]] trait for field descriptions.
 *
 */
case class UnmappedRead(
    name: String,
    sequence: Bases,
    baseQualities: IndexedSeq[Byte],
    isDuplicate: Boolean,
    failedVendorQualityChecks: Boolean,
    isPaired: Boolean) extends Read {

  assert(baseQualities.length == sequence.length)

  override val isMapped = false
  override def asMappedRead = None
}

