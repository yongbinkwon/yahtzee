package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.Single

class Yahtzee(
    private val yahtzeeDie: Die
): Combination(yahtzeeDice(yahtzeeDie)) {
    companion object {
        private fun yahtzeeDice(die: Die) = List(4) { die }
    }

    override fun score() = 36
    override fun subset() = setOf(
        Single.single(yahtzeeDie, 4),
        ThreeOfAKind(yahtzeeDice(yahtzeeDie))
    )
    override fun toString() = "Yahtzee!! That's ${score()} points :O"
}