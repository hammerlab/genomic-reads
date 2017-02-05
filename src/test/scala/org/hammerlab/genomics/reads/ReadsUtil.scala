package org.hammerlab.genomics.reads

import htsjdk.samtools.TextCigarCodec
import org.hammerlab.genomics.bases.{ Bases, BasesUtil }
import org.hammerlab.genomics.reads.Read.baseQualityStringToArray
import org.hammerlab.genomics.reference.{ ContigName, Locus }

trait ReadsUtil
  extends BasesUtil {

  /**
   * Convenience function to construct a Read from unparsed values.
   */
  private def read(sequence: Bases,
                   name: String,
                   baseQualities: String = "",
                   isDuplicate: Boolean = false,
                   contigName: ContigName = "",
                   alignmentQuality: Int = -1,
                   start: Int = -1,
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
      Locus(start),
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
               start: Int,
               chr: ContigName,
               qualityScores: Seq[Int]): MappedRead =
    makeRead(
      sequence,
      cigar,
      start,
      chr,
      qualityScores = Some(qualityScores)
    )

  def makeRead(sequence: Bases,
               cigar: String = "",
               start: Int = 1,
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

  def makePairedRead(chr: ContigName = "chr1",
                     start: Int = 1,
                     alignmentQuality: Int = 30,
                     isPositiveStrand: Boolean = true,
                     isMateMapped: Boolean = false,
                     mateReferenceContig: Option[String] = None,
                     mateStart: Option[Long] = None,
                     isMatePositiveStrand: Boolean = false,
                     sequence: String = "ACTGACTGACTG",
                     cigar: String = "12M",
                     inferredInsertSize: Option[Int]): PairedRead[MappedRead] = {

    val qualityScoreString = "@" * sequence.length

    PairedRead(
      read(
        sequence,
        name = "read1",
        cigarString = cigar,
        start = start,
        contigName = chr,
        isPositiveStrand = isPositiveStrand,
        baseQualities = qualityScoreString,
        alignmentQuality = alignmentQuality,
        isPaired = true
      ),
      isFirstInPair = true,
      mateAlignmentProperties =
        if (isMateMapped)
          Some(
            MateAlignmentProperties(
              mateReferenceContig.get,
              mateStart.get,
              inferredInsertSize = inferredInsertSize,
              isPositiveStrand = isMatePositiveStrand
            )
          )
        else
          None
    )
  }

  def makeReads(contigName: ContigName, reads: (String, String, Int)*): Seq[MappedRead] =
    for {
      (sequence, cigar, start) <- reads
    } yield
      makeRead(sequence, cigar, start, chr = contigName)

  def makeReads(reads: (String, String, Int)*): Seq[MappedRead] =
    for {
      (sequence, cigar, start) <- reads
    } yield
      makeRead(sequence, cigar, start)
}
