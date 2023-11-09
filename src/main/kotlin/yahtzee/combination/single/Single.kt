package yahtzee.combination.single

import yahtzee.Die
import yahtzee.Die.Companion.FOUR
import yahtzee.Die.Companion.ONE
import yahtzee.Die.Companion.THREE
import yahtzee.Die.Companion.TWO
import yahtzee.combination.Combination

abstract class Single(
    private val die: Die,
    private val frequency: Int
): Combination(List(frequency) { die }) {
    companion object {
        fun single(die: Die, frequency: Int) = when(die) {
            ONE -> Ones(frequency)
            TWO -> Twos(frequency)
            THREE -> Threes(frequency)
            FOUR -> Fours(frequency)
            else -> throw IllegalArgumentException("Die is $die but should be in range [1, 4]")
        }
    }
    override fun toString() = "$frequency counts of $die's: ${score()} points"
}