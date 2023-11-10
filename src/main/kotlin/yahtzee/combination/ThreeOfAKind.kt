package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.Single

class ThreeOfAKind(
    dice: List<Die>
): Combination(dice) {
    private fun numberOfEachDie(dice: List<Die>) = dice.groupingBy { it }.eachCount().entries
    private val threeOfAKindDice =
        numberOfEachDie(dice).firstOrNull { it.value >= 3 }?.key ?: throw IllegalArgumentException("No triplets found")

    override fun subset() = setOf(Single.single(threeOfAKindDice, 3))
    override fun result() = "Three $threeOfAKindDice's: ${score()} points"
    override fun toString() = "three of a kind"
}