package yahtzee.combination

import yahtzee.Die

object Ones: Single(Die.one()) {
    override fun toString() = "ones"
}