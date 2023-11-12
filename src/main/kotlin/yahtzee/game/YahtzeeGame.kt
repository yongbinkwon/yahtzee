package yahtzee.game

import yahtzee.Board
import yahtzee.Die
import yahtzee.combination.Combination
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreCard
import yahtzee.score.ScoreCardEntry

class YahtzeeGame {
    fun startGame(player1: String, player2: String) {
        var scoreCard1 = ScoreCard(playerName = player1)
        var scoreCard2 = ScoreCard(playerName = player2)
        while (!(scoreCard1.filledOut() && scoreCard2.filledOut())) {
            println("$scoreCard1's turn")
            scoreCard1 = playRound(scoreCard1)
            println("$scoreCard2's turn")
            scoreCard2 = playRound(scoreCard2)
        }
        val result1 = scoreCard1.result()
        val result2 = scoreCard2.result()

        println("$result1\n")
        println("$result2\n")
        println(result1 versus result2)
    }

    private fun playRound(scoreCard: ScoreCard) = Round(scoreCard).startRound() addTo scoreCard
}