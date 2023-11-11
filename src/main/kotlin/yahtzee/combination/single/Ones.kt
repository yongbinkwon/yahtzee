package yahtzee.combination.single

import yahtzee.Die

class Ones: Single(Die.one()) {
    override val combinationDescription = "ones"
}