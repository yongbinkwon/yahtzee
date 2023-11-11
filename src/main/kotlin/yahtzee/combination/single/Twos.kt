package yahtzee.combination.single

import yahtzee.Die

class Twos: Single(Die.two()) {
    override val combinationDescription = "twos"
}