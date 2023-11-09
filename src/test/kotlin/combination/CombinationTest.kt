package combination

import org.junit.jupiter.api.Nested
import yahtzee.Die
import yahtzee.combination.*
import yahtzee.combination.single.*
import kotlin.math.exp
import kotlin.random.Random
import kotlin.test.*

class CombinationTest {

    @Test
    fun `combinations are not equal even if same dices if not same type of combination`() {
        val dice = listOf(Die.ONE, Die.ONE, Die.ONE, Die.THREE)
        assertFalse { Chance(dice) as Combination == ThreeOfAKind(dice) as Combination }
    }

    @Test
    fun `combinations are equal if same dices and same type of combination`() {
        val dice = listOf(Die.ONE, Die.ONE, Die.ONE, Die.THREE)
        assertTrue { Chance(dice) == Chance(dice) }
    }

    @Test
    fun `combinations can't have zero die`() {
        assertFailsWith<IllegalArgumentException> { ThreeOfAKind(listOf()) }
    }

    @Test
    fun `combinations can't have more than four die`() {
        assertFailsWith<IllegalArgumentException> { ThreeOfAKind(List(5) { Die.ONE }) }
    }

    @Nested
    inner class SingleTest {
        private val seed = Random.Default
        private fun randomFrequency() = seed.nextInt(1, 5)
        private fun allSingles(frequency: Int = 1) =
            listOf(Ones(frequency), Twos(frequency), Threes(frequency), Fours(frequency))

        @Test
        fun `single() correctly generates singles`() {
            val frequency = randomFrequency()
            val expectedValues = allSingles(frequency)
            for ((i, expectedValue) in expectedValues.withIndex()) {
                val actualValue = Single.single(Die(i+1), frequency)
                assertEquals(expectedValue, actualValue)
            }
        }

        @Test
        fun `singles correctly has score equals frequency*dieValue`() {
            val frequency = randomFrequency()
            val singles = allSingles(frequency)
            for ((i, single) in singles.withIndex()) {
                val actualValue = (i+1)*frequency
                assertEquals(single.score(), actualValue)
            }
        }

        @Test
        fun `singles have no subset`() {
            val allSingles = allSingles()
            allSingles.forEach { assertEquals(setOf(), it.subset()) }
        }
    }

    @Nested
    inner class ChanceTest {
        @Test
        fun `Chance has no subset`() {
            val chance = Chance(listOf(Die.ONE, Die.TWO, Die.THREE, Die.FOUR))
            assertEquals(setOf(), chance.subset())
        }

        @Test
        fun `Chance correctly has score equals sum of dice`() {
            val randomDiceValues = List(4) { Random.Default.nextInt(1, 5) }
            val expectedValue = randomDiceValues.sum()
            val chance = Chance(randomDiceValues.map(::Die))
            assertEquals(expectedValue, chance.score())
        }

    }

    @Nested
    inner class StraightTest {
        @Test
        fun `Straight has subset equal to every single with frequency 1`() {
            val expectedValue = setOf(Ones(1), Twos(1), Threes(1), Fours(1))
            assertEquals(expectedValue, Straight().subset())
        }

        @Test
        fun `Straight correctly has score equals 20`() {
            assertEquals(20, Straight().score())
        }
    }

    @Nested
    inner class ThreeOfAKindTest {
        private val diceSetWithAllValues = listOf(Die.ONE, Die.TWO, Die.THREE, Die.FOUR)

        @Test
        fun `ThreeOfAKind has subset single with frequency three`() {
            val threeOfAKindDie = diceSetWithAllValues.random()
            val singleDie = diceSetWithAllValues.shuffled().first { it != threeOfAKindDie }
            val dice = listOf(threeOfAKindDie, threeOfAKindDie, threeOfAKindDie, singleDie).shuffled()
            val threeOfAKind = ThreeOfAKind(dice)

            val expectedValue = setOf(Single.single(threeOfAKindDie, 3))
            assertEquals(expectedValue, threeOfAKind.subset())
        }

        @Test
        fun `ThreeOfAKind correctly has score equals sum of dice`() {
            val threeOfAKindDie = diceSetWithAllValues.random()
            val singleDie = diceSetWithAllValues.shuffled().first { it != threeOfAKindDie }
            val dice = listOf(threeOfAKindDie, threeOfAKindDie, threeOfAKindDie, singleDie).shuffled()
            val threeOfAKind = ThreeOfAKind(dice)

            val expectedValue = dice.fold(0) { acc, die ->  die + acc}
            assertEquals(expectedValue, threeOfAKind.score())
        }

        @Test
        fun `ThreeOfAKind can have four equal die`() {
            val quintupleDie = diceSetWithAllValues.random()
            val dice = List(4) { quintupleDie }
            ThreeOfAKind(dice)
        }

        @Test
        fun `ThreeOfAKind fails if one of each die`() {
            assertFailsWith<IllegalArgumentException> { ThreeOfAKind(diceSetWithAllValues) }
        }

        @Test
        fun `ThreeOfAKind fails if maximum two identical die`() {
            val dice = listOf(Die.TWO, Die.TWO, Die.ONE, Die.FOUR)
            assertFailsWith<IllegalArgumentException> { ThreeOfAKind(dice) }
        }
    }

    @Nested
    inner class YahtzeeTest {
        private fun randomDie() = listOf(Die.ONE, Die.TWO, Die.THREE, Die.FOUR).random()

        @Test
        fun `Yahtzee has subset ThreeOfAKind with four identical and single with frequency four`() {
            val die = randomDie()
            val yahtzee = Yahtzee(die)
            val expectedValue = setOf(
                ThreeOfAKind(List(4) { die }),
                Single.single(die, 4)
            )
            assertEquals(expectedValue, yahtzee.subset())
        }

        @Test
        fun `Yahtzee correctly has score equals 36`() {
            assertEquals(36, Yahtzee(randomDie()).score())
        }
    }
}