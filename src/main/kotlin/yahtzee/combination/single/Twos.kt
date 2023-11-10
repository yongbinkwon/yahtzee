package yahtzee.combination.single

import yahtzee.Die

class Twos(frequency: Int): Single(Die.TWO, frequency) {
    override fun toString() = "twos"
}