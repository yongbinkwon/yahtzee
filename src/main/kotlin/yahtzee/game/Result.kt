package yahtzee.game

import yahtzee.combination.Combination

data class Result(
    private val playerName: String,
    private val combinations: List<Combination>,
    private val totalPoints: Int
) {
    override fun toString() = "$playerName\n\n" +
            "${combinationString()}\n\n" +
            "Total Points: $totalPoints"

    private fun combinationString() = combinations.joinToString("\n") {
        "$it: ${it.score()} points"
    }
}