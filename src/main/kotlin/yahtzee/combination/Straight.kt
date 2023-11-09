package yahtzee.combination

import yahtzee.Dice
import yahtzee.combination.single.*

class Straight: Combination(STRAIGHT) {
    companion object {
        private val STRAIGHT = listOf(Dice.ONE, Dice.TWO, Dice.THREE, Dice.FOUR)
    }

    override val score = 20
    override fun subset() = setOf(Ones(1), Twos(1), Threes(1), Fours(4))
    override fun toString() = "Straight: $score points"
}