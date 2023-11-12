package yahtzee.score

import yahtzee.Die
import yahtzee.combination.*
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreCard
import yahtzee.score.ScoreCardEntry
import yahtzee.score.YahtzeeResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ScoreCardTest {

    @Test
    fun `addScoreToCard() on empty score card returns new score card with one field filled`() {
        val dice = listOf(Die.one(), Die.one(), Die.two(), Die.one())
        val combination = ThreeOfAKind
        val entry = ScoreCardEntry(combination, combination.score(dice))
        val expectedScores = mapOf(combination to entry)
        val actualScoreCard = ScoreCard().addScoreToCard(combination, entry)
        assertEquals(ScoreCard(expectedScores), actualScoreCard)
    }

    @Test
    fun `addScoreToCard() on partially filled score card returns new score card with additional field filled`() {
        val dice = listOf(Die.one(), Die.one(), Die.two(), Die.one())
        val combination1 = ThreeOfAKind
        val combination2 = Chance
        val entry1 = ScoreCardEntry(combination1, combination1.score(dice))
        val entry2 = ScoreCardEntry(combination2, combination2.score(dice))
        val expectedScores = mapOf(combination1 to entry1, combination2 to entry2)
        val actualScoreCard = ScoreCard(mapOf(combination1 to entry1)).addScoreToCard(combination2, entry2)
        assertEquals(ScoreCard(expectedScores), actualScoreCard)
    }

    @Test
    fun `addScoreToCard() on field that is already filled fails`() {
        val dice = listOf(Die.one(), Die.one(), Die.two(), Die.one())
        val combination = ThreeOfAKind
        val entry = ScoreCardEntry(combination, combination.score(dice))
        val scoreCard = ScoreCard().addScoreToCard(combination, entry)
        assertFailsWith<IllegalStateException> { scoreCard.addScoreToCard(combination, entry) }
    }

    @Test
    fun `nonFilledRows() returns every combination when score card is not filled at all`() {
        assertEquals(Combination.ALL_COMBINATIONS, ScoreCard().nonFilledRows())
    }

    @Test
    fun `filledOut() returns true when every row is filled out`() {
        val entriesForEveryCombination = Combination.ALL_COMBINATIONS.fold(mapOf<Combination, ScoreCardEntry>()) { acc, combination ->
            acc + (combination to ScoreCardEntry(combination, 0))
        }
        assertTrue { ScoreCard(entriesForEveryCombination).filledOut() }
    }

    @Test
    fun `result() returns results of all filled entries`() {
        val score = 3
        val combination1 = Ones
        val entry1 = ScoreCardEntry(combination1, score)
        val combination2 = Fours
        val entry2 = ScoreCardEntry(combination2, score)
        val expectedScores = mapOf(combination1 to entry1, combination2 to entry2)
        val actualResults = YahtzeeResult("", listOf(entry1, entry2), score*2)
        assertEquals(ScoreCard(expectedScores).result(), actualResults)
    }

    @Test
    fun `string representation of ScoreCardEntry is correct`() {
        val combination = Chance
        val score = 6
        val entry = ScoreCardEntry(combination, score)
        val expectedStringRepresentation = "chance (sum of dice): 6 points"
        assertEquals(expectedStringRepresentation, entry.toString())
    }

    @Test
    fun `string representation of BlankEntry is correct`() {
        val combination = Chance
        val entry = BlankEntry(combination)
        val expectedStringRepresentation = "blank for chance (sum of dice): 0 points"
        assertEquals(expectedStringRepresentation, entry.toString())
    }
}