package yahtzee

import org.junit.jupiter.api.Test
import yahtzee.combination.*
import yahtzee.combination.single.Single
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun `3 of a kind`() {
        val threeOfAKindDie = diceSetWithAllValues().random()
        val singleDie = diceSetWithAllValues().shuffled().first { it != threeOfAKindDie }
        val dice =
            randomlyOrderedDice(threeOfAKindDie, threeOfAKindDie, threeOfAKindDie, singleDie)
        val board = Board(dice)
        val expectedCombinations = threeOfAKindCombinations(threeOfAKindDie, singleDie)
        assertEquals(expectedCombinations, board.combinations())
    }

    @Test
    fun straight() {
        val dice = randomlyOrderedDice(*diceSetWithAllValues().toTypedArray())
        val board = Board(dice)
        val expectedCombinations = setOf(
            Straight(),
            Chance(dice),
            Single.single(Dice.ONE, 1),
            Single.single(Dice.TWO, 1),
            Single.single(Dice.THREE, 1),
            Single.single(Dice.FOUR, 1),
        )
        assertEquals(expectedCombinations, board.combinations())
    }

    @Test
    fun yahtzee() {
        val yahtzeeDie = diceSetWithAllValues().random()
        val dice = List(4) { yahtzeeDie }
        val board = Board(dice)
        val expectedCombinations = setOf(
            Yahtzee(yahtzeeDie), ThreeOfAKind(dice), Single.single(yahtzeeDie, 4), Chance(dice)
        )
        assertEquals(expectedCombinations, board.combinations())
    }

    private fun diceSetWithAllValues() = listOf(Dice.ONE, Dice.TWO, Dice.THREE, Dice.FOUR)

    private fun randomlyOrderedDice(vararg dice: Dice) = dice.apply { shuffle() }.toList()

    private fun threeOfAKindCombinations(theeOfAKindDie: Dice, singleDie: Dice): Set<Combination> {
        val dice = listOf(theeOfAKindDie, theeOfAKindDie, theeOfAKindDie, singleDie)
        return setOf(ThreeOfAKind(dice), Single.single(theeOfAKindDie, 3), Single.single(singleDie, 1), Chance(dice))
    }
}