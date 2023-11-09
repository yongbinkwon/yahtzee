package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.*

class Straight: Combination(STRAIGHT) {
    companion object {
        private val STRAIGHT = listOf(Die.ONE, Die.TWO, Die.THREE, Die.FOUR)
    }

    override val score = 20
    override fun subset() = setOf(Ones(1), Twos(1), Threes(1), Fours(1))
    override fun toString() = "Straight: $score points"
}