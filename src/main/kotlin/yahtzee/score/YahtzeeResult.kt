package yahtzee.score

data class YahtzeeResult(
    private val player: String,
    private val scoreCard: List<ScoreCardEntry>,
    private val totalScore: Int
) {
    override fun toString() = "score card for $player\n${scoreCardStringRepresentation()}\ntotal score: $totalScore"
    private fun scoreCardStringRepresentation() = scoreCard.joinToString("\n")

    infix fun versus(other: YahtzeeResult): String {
        val result = totalScore.compareTo(other.totalScore)
        return when {
            result == 0 -> "draw"
            result < 0 -> "winner: ${other.player}"
            else -> "winner: $player"
        }
    }
}