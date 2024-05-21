package yahtzee.score

import yahtzee.combination.*

class ScoreCard(
    private val scores: CombinationScores = mapOf(),
    private val playerName: String = ""
) {
    fun addScoreToCard(combination: Combination, entry: ScoreCardEntry) =
        scores[combination]?.run {
            throw IllegalStateException("combination $combination is already in score card")
        } ?: ScoreCard(scoreCardRow(combination, entry) addTo scores, playerName)

    private infix fun ScoreCardRow.addTo(scores: CombinationScores) = scores + this
    private fun scoreCardRow(combination: Combination, entry: ScoreCardEntry) = combination to entry

    fun nonFilledRows() = Combination.ALL_COMBINATIONS.filter { it !in scores.keys }.toSet()
    fun filledOut() = scores.keys == Combination.ALL_COMBINATIONS
    private fun totalScore() = scores.values.fold(0) { acc, entry -> entry + acc }

    fun result() = YahtzeeResult(playerName, scores.values.toList(), totalScore())

    override fun toString() = playerName
    override fun equals(other: Any?) = this === other || (other is ScoreCard && this.equals(other))
    private fun equals(other: ScoreCard) = playerName == other.playerName && scores == other.scores
    override fun hashCode() = (31 * playerName.hashCode()) + scores.hashCode()
}

private typealias CombinationScores = Map<out Combination, ScoreCardEntry>
private typealias ScoreCardRow = Pair<Combination, ScoreCardEntry>