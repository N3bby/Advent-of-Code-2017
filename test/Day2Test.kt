import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun test_findDivisibleNumbers() {
        assertEquals(Pair(8, 2), findDivisibleNumbers(listOf(5, 9, 2, 8)))
        assertEquals(Pair(9, 3), findDivisibleNumbers(listOf(9, 4, 7, 3)))
        assertEquals(Pair(6, 3), findDivisibleNumbers(listOf(3, 8, 6, 5)))
    }

}