package yahtzee.combination.single

import yahtzee.Die

object Fours: Single(Die.four()) {
    override val combinationDescription = "fours"
}