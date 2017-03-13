package org.hammerlab.genomics.reads

import htsjdk.samtools.TextCigarCodec
import org.hammerlab.genomics.bases.{ Bases, BasesUtil }
import org.hammerlab.genomics.reads.Read.baseQualityStringToArray
import org.hammerlab.genomics.reference.test.LociConversions.intToLocus
import org.hammerlab.genomics.reference.{ ContigName, Locus }

trait ReadsUtil
  extends BasesUtil {

  implicit def contigNameFactory: ContigName.Factory = ContigName.Strict

  /**
   * Convenience function to construct a Read from unparsed values.
   */
  private def read(sequence: Bases,
                   name: String,
                   contigName: ContigName,
                   baseQualities: String = "",
                   isDuplicate: Boolean = false,
                   alignmentQuality: Int = -1,
                   start: Locus = Locus(-1),
                   cigarString: String = "",
                   failedVendorQualityChecks: Boolean = false,
                   isPositiveStrand: Boolean = true,
                   isPaired: Boolean = true) = {

    val qualityScoresArray = baseQualityStringToArray(baseQualities, sequence.length)

    val cigar = TextCigarCodec.decode(cigarString)
    MappedRead(
      name,
      sequence,
      qualityScoresArray,
      isDuplicate,
      contigName,
      alignmentQuality,
      start,
      cigar,
      failedVendorQualityChecks,
      isPositiveStrand,
      isPaired
    )
  }

  def makeRead(sequence: Bases,
               cigar: String,
               qualityScores: Seq[Int]): MappedRead =
    makeRead(sequence, cigar, qualityScores = Some(qualityScores))

  def makeRead(sequence: Bases,
               cigar: String,
               start: Locus,
               chr: ContigName,
               qualityScores: Seq[Int]): MappedRead =
    makeRead(
      sequence,
      cigar,
      start,
      chr,
      qualityScores = Some(qualityScores)
    )

  protected def makeRead(sequence: Bases,
                         cigar: String = "",
                         start: Locus = Locus(1),
                         chr: ContigName = "chr1",
                         qualityScores: Option[Seq[Int]] = None,
                         alignmentQuality: Int = 30): MappedRead = {

    val qualityScoreString =
      if (qualityScores.isDefined)
        qualityScores.get.map(q => q + 33).map(_.toChar).mkString
      else
        "@" * sequence.length

    read(
      sequence,
      name = "read1",
      cigarString = cigar,
      start = start,
      contigName = chr,
      baseQualities = qualityScoreString,
      alignmentQuality = alignmentQuality
    )
  }

  implicit def makeBasesCigarStart(t: (String, String, Int)): (Bases, String, Locus) = (t._1, t._2, t._3)

  def makeReads(contigName: ContigName, reads: (Bases, String, Locus)*): Seq[MappedRead] =
    for {
      (sequence, cigar, start) <- reads
    } yield
      makeRead(sequence, cigar, start, chr = contigName)

  def makeReads(reads: (Bases, String, Locus)*): Seq[MappedRead] =
    for {
      (sequence, cigar, start) <- reads
    } yield
      makeRead(sequence, cigar, start, chr = "chr1")
}
