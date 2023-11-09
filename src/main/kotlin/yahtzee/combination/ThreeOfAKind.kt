package yahtzee.combination

import yahtzee.Dice
import yahtzee.combination.single.Single

class ThreeOfAKind(
    dice: List<Dice>
): Combination(dice) {
    private fun numberOfEachDie(dice: List<Dice>) = dice.groupingBy { it }.eachCount().entries
    private val threeOfAKindDice =
        numberOfEachDie(dice).firstOrNull { it.value >= 3 }?.key ?: throw IllegalArgumentException("No triplets found")

    override fun subset() = setOf(Single.single(threeOfAKindDice, 3))
    override fun toString() = "Three $threeOfAKindDice's: $score points"
}