package yahtzee.score

import yahtzee.combination.*
import yahtzee.combination.single.Fours
import yahtzee.combination.single.Ones
import yahtzee.combination.single.Threes
import yahtzee.combination.single.Twos

class ScoreSheet(
    private val playerName: String,
    private val scores: CombinationScores = mapOf()
) {
    companion object {
        private val COMBINATIONS = setOf(Ones, Twos, Threes, Fours, ThreeOfAKind, Yahtzee, Straight, Chance)
    }

    fun addScoreToSheet(combination: Combination, entry: ScoreSheetEntry) =
        scores[combination]?.run {
            throw IllegalStateException("combination $combination is already in score sheet")
        } ?: ScoreSheet(playerName, scoreSheetRow(combination, entry) addTo scores)

    private infix fun ScoreSheetRow.addTo(scores: CombinationScores) = scores + this
    private fun scoreSheetRow(combination: Combination, entry: ScoreSheetEntry) = combination to entry

    fun nonFilledRows() = scores.entries.filter { it.key !in COMBINATIONS }.map { it.key }
    fun filledOut() = scores.keys == COMBINATIONS
    fun totalScore() = scores.values.fold(0) { acc, entry -> entry + acc }

    override fun equals(other: Any?) = this === other || (other is ScoreSheet && this.equals(other))
    private fun equals(other: ScoreSheet) = playerName == other.playerName && scores == other.scores
    override fun hashCode() = (31 * playerName.hashCode()) + scores.hashCode()

    /*
    fun results() = Result(playerName, sheet.values.filterNotNull().toList(), totalScore())
    infix fun versus(other: ScoreSheet) {
        when(totalScore().compareTo(other.totalScore())) {
            -1 -> println("${other.playerName} wins")
            0 -> println("draw")
            1 -> println("$playerName wins")
        }
    }
    private fun totalScore() = sheet.values.sumOf { it?.score() ?: 0 }

     */
}

private typealias CombinationScores = Map<Combination, ScoreSheetEntry>
private typealias ScoreSheetRow = Pair<Combination, ScoreSheetEntry>