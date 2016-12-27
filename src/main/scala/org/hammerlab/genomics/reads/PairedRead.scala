package org.hammerlab.genomics.reads

import org.hammerlab.genomics.bases.Bases

/**
 * PairedRead is a MappedRead or UnmappedRead with the additional mate information
 *
 * @param read Unmapped or MappedRead base read
 * @param isFirstInPair Whether the read is earlier that the the mate read
 * @param mateAlignmentProperties Alignment location of the mate if it the mate is aligned
 * @tparam T UnmappedRead or MappedRead
 */
case class PairedRead[+T <: Read](read: T,
                                  isFirstInPair: Boolean,
                                  mateAlignmentProperties: Option[MateAlignmentProperties])
  extends Read {

  @transient val isMateMapped = mateAlignmentProperties.isDefined
  @transient override val name: String = read.name
  @transient override val failedVendorQualityChecks: Boolean = read.failedVendorQualityChecks
  @transient override val baseQualities: IndexedSeq[Byte] = read.baseQualities
  @transient override val isDuplicate: Boolean = read.isDuplicate
  @transient override val sequence: Bases = read.sequence
  @transient override val isPaired: Boolean = true
  @transient override val isMapped = read.isMapped
  @transient override def asMappedRead = read.asMappedRead
}
