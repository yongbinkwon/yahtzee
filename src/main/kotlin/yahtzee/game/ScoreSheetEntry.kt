package yahtzee.game

import yahtzee.combination.Combination
import yahtzee.combination.CombinationType

data class ScoreSheetEntry(
    val field: CombinationType,
    val combination: Combination?
)