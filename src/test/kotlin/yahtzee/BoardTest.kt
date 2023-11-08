package yahtzee

import org.junit.jupiter.api.Test
import yahtzee.combination.*
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun `3 of a kind`() {
        val threeOfAKindDiceValue = (1..4).random()
        val singleDiceValue = (1..4).shuffled().first { it != threeOfAKindDiceValue }
        val diceValues =
            randomlyOrderedDiceValues(threeOfAKindDiceValue, threeOfAKindDiceValue, threeOfAKindDiceValue, singleDiceValue)
        val board = Board(*diceValues)
        val expectedCombinations = threeOfAKindCombinations(threeOfAKindDiceValue, singleDiceValue)
        assertEquals(expectedCombinations, board.combinations())
    }

    @Test
    fun straight() {
        val diceValues = randomlyOrderedDiceValues(1, 2, 3, 4)
        val board = Board(*diceValues)
        val expectedCombinations = setOf(
            Straight(),
            Chance(diceValues.sum()),
            Single(1, 1),
            Single(2, 1),
            Single(3, 1),
            Single(4, 1)
        )
        assertEquals(expectedCombinations, board.combinations())
    }

    @Test
    fun yahtzee() {
        val diceValue = (1..4).random()
        val board = Board(diceValue, diceValue, diceValue, diceValue)
        val expectedCombinations = setOf(
            Yahtzee(diceValue), ThreeOfAKind(diceValue, diceValue*4), Single(diceValue, 4), Chance(diceValue*4)
        )
        assertEquals(expectedCombinations, board.combinations())
    }

    private fun randomlyOrderedDiceValues(vararg diceValue: Int) = diceValue.apply { shuffle() }

    private fun threeOfAKindCombinations(threeOfAKindDiceValue: Int, singleDiceValue: Int) = setOf(
        ThreeOfAKind(threeOfAKindDiceValue, 3*threeOfAKindDiceValue + singleDiceValue),
        Single(threeOfAKindDiceValue, 3),
        Single(singleDiceValue, 1),
        Chance(3*threeOfAKindDiceValue + singleDiceValue)
    )
}