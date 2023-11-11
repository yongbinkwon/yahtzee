package yahtzee

import org.junit.jupiter.api.Test
import yahtzee.combination.*
import yahtzee.combination.single.*
import kotlin.test.assertEquals

class BoardTest {

    @Test
    fun `3 of a kind`() {
        val threeOfAKindDie = diceSetWithAllValues().random()
        val singleDie = diceSetWithAllValues().shuffled().first { it != threeOfAKindDie }
        val dice =
            randomlyOrderedDice(listOf(threeOfAKindDie, threeOfAKindDie, threeOfAKindDie, singleDie))
        val board = Board(dice)
        val expectedCombinations = setOf(ThreeOfAKind, Single.single(singleDie), Single.single(threeOfAKindDie), Chance)
        assertEquals(expectedCombinations, board.combinations())
    }


    @Test
    fun straight() {
        val dice = randomlyOrderedDice(diceSetWithAllValues())
        val board = Board(dice)
        val expectedCombinations = setOf(Straight, Chance, Ones, Twos, Threes, Fours)
        assertEquals(expectedCombinations, board.combinations())
    }

    @Test
    fun yahtzee() {
        val yahtzeeDie = diceSetWithAllValues().random()
        val dice = List(4) { yahtzeeDie }
        val board = Board(dice)
        val expectedCombinations = setOf(Yahtzee, ThreeOfAKind, Single.single(yahtzeeDie), Chance)
        assertEquals(expectedCombinations, board.combinations())
    }

    private fun diceSetWithAllValues() = listOf(Die.one(), Die.two(), Die.three(), Die.four())

    private fun randomlyOrderedDice(dice: List<Die>) = dice.shuffled()
}