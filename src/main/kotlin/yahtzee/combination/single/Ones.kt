package yahtzee.combination.single

import yahtzee.Die

object Ones: Single(Die.one()) {
    override val combinationDescription = "ones"
}