package yahtzee.score

import yahtzee.combination.Combination
import yahtzee.combination.CombinationType

data class ScoreSheetEntry(
    val combinationDescription: String,
    val score: Int
) {
    override fun toString() = "$combinationDescription: $score points"
}