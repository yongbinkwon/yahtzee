package yahtzee.score

import yahtzee.combination.*
import yahtzee.combination.Fours
import yahtzee.combination.Ones
import yahtzee.combination.Threes
import yahtzee.combination.Twos

class ScoreCard(
    private val playerName: String,
    private val scores: CombinationScores = mapOf()
) {
    companion object {
        private val COMBINATIONS = setOf(Ones, Twos, Threes, Fours, ThreeOfAKind, Yahtzee, Straight, Chance)
    }

    fun addScoreToCard(combination: Combination, entry: ScoreCardEntry) =
        scores[combination]?.run {
            throw IllegalStateException("combination $combination is already in score card")
        } ?: ScoreCard(playerName, scoreCardRow(combination, entry) addTo scores)

    private infix fun ScoreCardRow.addTo(scores: CombinationScores) = scores + this
    private fun scoreCardRow(combination: Combination, entry: ScoreCardEntry) = combination to entry

    fun nonFilledRows() = scores.entries.filter { it.key !in COMBINATIONS }.map { it.key }
    fun filledOut() = scores.keys == COMBINATIONS
    fun totalScore() = scores.values.fold(0) { acc, entry -> entry + acc }

    override fun equals(other: Any?) = this === other || (other is ScoreCard && this.equals(other))
    private fun equals(other: ScoreCard) = playerName == other.playerName && scores == other.scores
    override fun hashCode() = (31 * playerName.hashCode()) + scores.hashCode()

    /*
    fun results() = Result(playerName, card.values.filterNotNull().toList(), totalScore())
    infix fun versus(other: ScoreCard) {
        when(totalScore().compareTo(other.totalScore())) {
            -1 -> println("${other.playerName} wins")
            0 -> println("draw")
            1 -> println("$playerName wins")
        }
    }
    private fun totalScore() = card.values.sumOf { it?.score() ?: 0 }

     */
}

private typealias CombinationScores = Map<Combination, ScoreCardEntry>
private typealias ScoreCardRow = Pair<Combination, ScoreCardEntry>