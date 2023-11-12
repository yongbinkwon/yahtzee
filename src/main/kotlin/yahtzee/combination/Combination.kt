package yahtzee.combination

import yahtzee.Die


sealed class Combination {
    companion object {
        val ALL_COMBINATIONS = Combination::class.sealedSubclasses.mapNotNull { it.objectInstance }.toSet()
    }

    abstract fun score(dice: List<Die> = listOf()): Int
    protected fun sumOfDice(dice: List<Die>) = dice.fold(0) { acc, die -> die + acc}
}