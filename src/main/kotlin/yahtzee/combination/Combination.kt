package yahtzee.combination

import yahtzee.Die
import yahtzee.score.ScoreSheetEntry


abstract class Combination {
    protected abstract val combinationDescription: String
    protected abstract fun score(dice: List<Die>): Int

    fun scoreSheetEntry(dice: List<Die>) = ScoreSheetEntry(combinationDescription, score(dice))
    fun blankEntry() = ScoreSheetEntry("fill blank for $combinationDescription", 0)

    protected fun sumOfDice(dice: List<Die>) = dice.fold(0) { acc, die -> die + acc}
}