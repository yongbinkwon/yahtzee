package yahtzee

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DieTest {

    @Test
    fun `die can have integer values in range 1 to 4`() {
        for (i in 1..4) {
            Die(i)
        }
    }

    @Test
    fun `die can not have negative values`() {
        val illegalDieValue = Random.Default.nextInt(-100, 0)
        assertFailsWith<IllegalArgumentException> { Die(illegalDieValue) }
    }

    @Test
    fun `die can not have value zero`() {
        assertFailsWith<IllegalArgumentException> { Die(0) }
    }

    @Test
    fun `die can not have values above 4`() {
        val illegalDieValue = Random.Default.nextInt(5, 100)
        assertFailsWith<IllegalArgumentException> { Die(illegalDieValue) }
    }

    @Test
    fun `die correctly changes value when rolled`() {
        val expectedSeed = Random(123)
        val actualSeed = Random(123)

        val expectedDice = List(10) { expectedSeed.nextInt(1, 5) }.map(::Die)
        val acutalDie = Die(actualSeed)

        assertEquals(expectedDice[0], acutalDie)
        expectedDice.drop(1).forEach {
            assertEquals(it, acutalDie.roll())
        }
    }

    @Test
    fun `value of die is correctly added`() {
        val dieValue = Random.Default.nextInt(1, 5)
        val addend = Random.Default.nextInt(1, 100)
        val expectedResult = dieValue+addend
        val actualResult = Die(dieValue) + addend
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `value of die is correctly multiplied`() {
        val dieValue = Random.Default.nextInt(1, 5)
        val multiplier = Random.Default.nextInt(1, 5)
        val expectedResult = dieValue*multiplier
        val actualResult = Die(dieValue)*multiplier
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `two die are equal if their values are the same regardless of their seed`() {
        val die1 = Die(Random(123), 1)
        val die2 = Die(Random(321), 1)
        assertTrue { die1 == die2 }
    }
}