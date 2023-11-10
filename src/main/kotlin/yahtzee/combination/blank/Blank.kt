package yahtzee.combination.blank

import yahtzee.combination.*
import yahtzee.combination.single.Fours
import yahtzee.combination.single.Ones
import yahtzee.combination.single.Threes
import yahtzee.combination.single.Twos
import kotlin.reflect.KClass

class Blank private constructor(private val typeOfCombination: String): Combination(listOf()) {
    companion object {
        fun blank(combinationType: CombinationType) = when(combinationType) {
            Ones::class -> Blank("ones")
            Twos::class -> Blank("twos")
            Threes::class -> Blank("threes")
            Fours::class -> Blank("fours")
            ThreeOfAKind::class -> Blank("three of a kind")
            Yahtzee::class -> Blank("yahtzee")
            Straight::class -> Blank("straight")
            Chance::class -> Blank("chance")
            else -> throw IllegalStateException("new combination detected")
        }
    }

    override fun result() = "Filling blank for $typeOfCombination: 0 points"
}

private typealias CombinationType = KClass<out Combination>