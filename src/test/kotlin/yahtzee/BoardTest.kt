package yahtzee

import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun singles() {

    }

    @Test
    fun `3 of a kind`() {
        val board = Board(mutableMapOf(1 to 3, 3 to 1))
    }

    @Test
    fun straight() {

    }

    @Test
    fun yahtzee() {

    }
}