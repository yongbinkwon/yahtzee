package score

import yahtzee.combination.Combination
import kotlin.reflect.KClass
import kotlin.test.Test

class ScoreCardTest {

    @Test
    fun `score card`() {
        println(getAllSingletonsImplementing())
    }

    private fun getAllSingletonsImplementing(): Collection<KClass<*>> {
        return Combination::class.sealedSubclasses
    }
}