package yahtzee.score

import yahtzee.combination.Combination

open class ScoreSheetEntry(
    private val combination: Combination,
    private val score: Int
) {
    override fun toString() = "$combination: $score points"

    infix fun addTo(scoreSheet: ScoreSheet) { scoreSheet.addScoreToSheet(combination, this) }
}