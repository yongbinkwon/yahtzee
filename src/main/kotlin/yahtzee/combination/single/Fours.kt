package yahtzee.combination.single

import yahtzee.Die

object Fours: Single(Die.four()) {
    override fun toString() = "fours"
}