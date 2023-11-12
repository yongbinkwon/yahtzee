package combination

import org.junit.jupiter.api.Nested
import yahtzee.Die
import yahtzee.combination.*
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class CombinationTest {
    @Nested
    inner class SingleTest {
        private val everySingle = listOf(Ones, Twos, Threes, Fours)
        private val everyDie = listOf(Die.one(), Die.two(), Die.three(), Die.four())

        @Test
        fun `single() correctly generates singles`() {
            for ((i, expectedValue) in everySingle.withIndex()) {
                val actualValue = singleDieCombination(Die(i+1))
                assertEquals(expectedValue, actualValue)
            }
        }

        @Test
        fun `singles correctly has score equals frequency times dieValue`() {
            val dice = List(4) { Die() }
            everyDie.forEach { die ->
                val expectedScore = die*dice.count { it == die }
                assertEquals(expectedScore, singleDieCombination(die).score(dice))
            }
        }
    }

    @Nested
    inner class ChanceTest {
        @Test
        fun `Chance correctly has score equals sum of dice`() {
            val randomDiceValues = List(4) { Random.Default.nextInt(1, 5) }
            val expectedValue = randomDiceValues.sum()
            assertEquals(expectedValue, Chance.score(randomDiceValues.map(::Die)))
        }

    }

    @Nested
    inner class StraightTest {
        @Test
        fun `Straight correctly has score equals 20`() {
            assertEquals(20, Straight.score())
        }
    }

    @Nested
    inner class ThreeOfAKindTest {
        private val diceSetWithAllValues = listOf(Die.one(), Die.two(), Die.three(), Die.four())

        @Test
        fun `ThreeOfAKind correctly has score equals sum of dice`() {
            val threeOfAKindDie = diceSetWithAllValues.random()
            val singleDie = diceSetWithAllValues.shuffled().first { it != threeOfAKindDie }
            val dice = listOf(threeOfAKindDie, threeOfAKindDie, threeOfAKindDie, singleDie).shuffled()

            val expectedValue = dice.fold(0) { acc, die ->  die + acc}
            assertEquals(expectedValue, ThreeOfAKind.score(dice))
        }
    }

    @Nested
    inner class YahtzeeTest {
        private fun randomYahtzeeDice() = List(4) { listOf(Die.one(), Die.two(), Die.three(), Die.four()).random() }

        @Test
        fun `Yahtzee correctly has score equals 36`() {
            assertEquals(36, Yahtzee.score(randomYahtzeeDice()))
        }
    }
}