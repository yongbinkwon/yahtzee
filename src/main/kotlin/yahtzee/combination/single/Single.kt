package yahtzee.combination.single

import yahtzee.Dice
import yahtzee.Dice.Companion.FOUR
import yahtzee.Dice.Companion.ONE
import yahtzee.Dice.Companion.THREE
import yahtzee.Dice.Companion.TWO
import yahtzee.combination.Combination

abstract class Single(
    private val dice: Dice,
    private val frequency: Int
): Combination(List(frequency) { dice }) {
    companion object {
        fun single(dice: Dice, frequency: Int) = when(dice) {
            ONE -> Ones(frequency)
            TWO -> Twos(frequency)
            THREE -> Threes(frequency)
            FOUR -> Fours(frequency)
            else -> throw IllegalArgumentException("Dice is $dice but should be in range [1, 4]")
        }
    }
    override fun toString() = "$frequency counts of $dice's: $score points"
}