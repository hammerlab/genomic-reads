package org.hammerlab.genomics.reads

import htsjdk.samtools.{ Cigar, CigarElement }
import org.bdgenomics.adam.util.PhredUtils.phredToSuccessProbability
import org.hammerlab.genomics.bases.Bases
import org.hammerlab.genomics.cigar.Element
import org.hammerlab.genomics.reference.{ ContigName, Locus, Region, WindowSize }

import scala.collection.JavaConversions

/**
 * A mapped read. See the [[Read]] trait for some of the field descriptions.
 *
 * @param contigName the contig name (e.g. "chr12") that this read was mapped to.
 * @param alignmentQuality the mapping quality, phred scaled.
 * @param start the (0-based) reference locus that the first base in this read aligns to.
 * @param cigar parsed samtools CIGAR object.
 */
case class MappedRead(
    name: String,
    sequence: Bases,
    baseQualities: IndexedSeq[Byte],
    isDuplicate: Boolean,
    contigName: ContigName,
    alignmentQuality: Int,
    start: Locus,
    cigar: Cigar,
    failedVendorQualityChecks: Boolean,
    isPositiveStrand: Boolean,
    isPaired: Boolean)
  extends Read

    with Region {

  assert(
    baseQualities.length == sequence.length.size,
    s"Base qualities have length ${baseQualities.length} but sequence has length ${sequence.length}"
  )

  override val isMapped = true
  override def asMappedRead = Some(this)

  lazy val alignmentLikelihood = phredToSuccessProbability(alignmentQuality)

  /** Individual components of the CIGAR string (e.g. "10M"), parsed, and as a Scala buffer. */
  @transient val cigarElements: Vector[CigarElement] =
    JavaConversions
      .asScalaIterator(cigar.getCigarElements.iterator())
      .toVector

  /**
   * The end of the alignment, exclusive. This is the first reference locus AFTER the locus corresponding to the last
   * base in this read.
   */
  val end: Locus = start + (cigar.getPaddedReferenceLength: WindowSize)

  /**
   * A read can be "clipped", meaning that some prefix or suffix of it did not align. This is the start of the whole
   * read's alignment, including any initial clipped bases.
   */
  val unclippedStart =
    cigarElements
      .takeWhile(_.isClipped)
      .foldLeft(start)(_ + _)

  /**
   * The end of the read's alignment, including any final clipped bases, exclusive.
   */
  val unclippedEnd =
    cigarElements
      .reverse
      .takeWhile(_.isClipped)
      .foldLeft(end)(_ - _)

  override def toString: String = s"MappedRead($contigName:$start, $cigar, $sequence)"
}
