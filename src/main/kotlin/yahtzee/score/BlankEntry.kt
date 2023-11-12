package yahtzee.score

import yahtzee.combination.Combination

class BlankEntry(
    combination: Combination,
) : ScoreCardEntry(combination, 0) {
    override fun toString() = "blank for ${super.toString()}"
}