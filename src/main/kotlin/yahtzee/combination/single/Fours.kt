package yahtzee.combination.single

import yahtzee.Die

class Fours: Single(Die.four()) {
    override val combinationDescription = "fours"
}