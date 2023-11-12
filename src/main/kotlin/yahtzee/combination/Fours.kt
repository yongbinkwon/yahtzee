package yahtzee.combination

import yahtzee.Die

object Fours: Single(Die.four()) {
    override fun toString() = "fours"
}