package yahtzee.combination

import yahtzee.Die

object Threes: Single(Die.three()) {
    override fun toString() = "threes"
}