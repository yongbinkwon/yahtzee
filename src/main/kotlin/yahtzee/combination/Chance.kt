package yahtzee.combination

import yahtzee.Dice

class Chance(
    dice: List<Dice>
): Combination(dice) {
    override fun toString() = "Chance (sum of all dice): $score points"
}