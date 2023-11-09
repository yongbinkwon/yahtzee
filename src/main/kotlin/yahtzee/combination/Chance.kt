package yahtzee.combination

import yahtzee.Die

class Chance(
    dice: List<Die>
): Combination(dice) {
    override fun toString() = "Chance (sum of all dice): ${score()} points"
}