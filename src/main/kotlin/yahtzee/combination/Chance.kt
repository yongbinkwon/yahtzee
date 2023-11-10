package yahtzee.combination

import yahtzee.Die

class Chance(
    dice: List<Die>
): Combination(dice) {
    override fun result() = "Chance (sum of all dice): ${score()} points"
    override fun toString() = "chance"
}