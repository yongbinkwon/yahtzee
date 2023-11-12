package yahtzee.score

import yahtzee.combination.Combination

open class ScoreCardEntry(
    private val combination: Combination,
    private val score: Int
) {
    infix fun addTo(scoreCard: ScoreCard) = scoreCard.addScoreToCard(combination, this)

    override fun toString() = "$combination: $score points"
    operator fun plus(other: Int) = score + other
}