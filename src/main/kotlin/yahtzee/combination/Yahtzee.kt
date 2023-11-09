package yahtzee.combination

import yahtzee.Dice
import yahtzee.combination.single.Single

class Yahtzee(
    private val yahtzeeDice: Dice
): Combination(yahtzeeDice(yahtzeeDice)) {
    companion object {
        private fun yahtzeeDice(die: Dice) = List(4) { die }
    }

    override val score = 36
    override fun subset() = setOf(
        Single.single(yahtzeeDice, 4),
        ThreeOfAKind(yahtzeeDice(yahtzeeDice))
    )
    override fun toString() = "Yahtzee!! That's $score points :O"
}