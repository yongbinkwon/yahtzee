package yahtzee.combination

import yahtzee.Die
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreSheetEntry


abstract class Combination {
    protected abstract fun score(dice: List<Die>): Int

    fun scoreSheetEntry(dice: List<Die>) = ScoreSheetEntry(this, score(dice))
    fun blankEntry() = BlankEntry(this)

    protected fun sumOfDice(dice: List<Die>) = dice.fold(0) { acc, die -> die + acc}
}