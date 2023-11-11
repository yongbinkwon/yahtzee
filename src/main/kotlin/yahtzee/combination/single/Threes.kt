package yahtzee.combination.single

import yahtzee.Die

class Threes: Single(Die.three()) {
    override val combinationDescription = "threes"
}