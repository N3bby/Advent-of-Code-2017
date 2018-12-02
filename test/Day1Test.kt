import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun test_findConsecutiveDigits() {
        assertEquals(listOf(1, 2), findConsecutiveDigits("1122", 1))
        assertEquals(listOf(1, 1, 1, 1), findConsecutiveDigits("1111", 1))
        assertEquals(listOf(), findConsecutiveDigits("1234", 1))
        assertEquals(listOf(9), findConsecutiveDigits("91212129", 1))
        assertEquals(listOf(1, 2, 1, 2), findConsecutiveDigits("1212", 2))
    }

}