package yahtzee.combination

import yahtzee.Die

class Chance: Combination() {
    override val combinationDescription = "chance (sum of dice)"
    override fun score(dice: List<Die>) = sumOfDice(dice)

    override fun toString() = "chance"
}