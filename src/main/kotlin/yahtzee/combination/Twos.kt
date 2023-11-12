package yahtzee.combination

import yahtzee.Die

object Twos: Single(Die.two()) {
    override fun toString() = "twos"
}