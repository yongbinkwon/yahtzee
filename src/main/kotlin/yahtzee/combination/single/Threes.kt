package yahtzee.combination.single

import yahtzee.Die

object Threes: Single(Die.three()) {
    override val combinationDescription = "threes"
}