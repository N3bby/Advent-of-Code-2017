import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun test_generateSpiralNumbers() {
        val spiralNumbers = generateSpiralNumbers(11).toList().sortedBy(SpiralNumber::number)
        assertEquals(SpiralNumber(1, 0, 0), spiralNumbers[0])
        assertEquals(SpiralNumber(2, 1, 0), spiralNumbers[1])
        assertEquals(SpiralNumber(3, 1, 1), spiralNumbers[2])
        assertEquals(SpiralNumber(4, 0, 1), spiralNumbers[3])
        assertEquals(SpiralNumber(5, -1, 1), spiralNumbers[4])
        assertEquals(SpiralNumber(6, -1, 0), spiralNumbers[5])
        assertEquals(SpiralNumber(7, -1, -1), spiralNumbers[6])
        assertEquals(SpiralNumber(8, 0, -1), spiralNumbers[7])
        assertEquals(SpiralNumber(9, 1, -1), spiralNumbers[8])
        assertEquals(SpiralNumber(10, 2, -1), spiralNumbers[9])
        assertEquals(SpiralNumber(11, 2, 0), spiralNumbers[10])
    }

    @Test
    fun test_getNextSpiralNumber_goesUp_ifNoTopNeighbour_butLeftNeighbour() {
        val nextCoordinate = getNextSpiralNumber(
            SpiralNumber(2, 1, 0),
            setOf(
                SpiralNumber(1, 0, 0)
            )
        )
        assertEquals(SpiralNumber(3, 1, 1), nextCoordinate)
    }

    @Test
    fun test_getNextSpiralCoordinate_goesLeft_ifNoLeftNeighbour_butBottomNeighbour() {
        val nextCoordinate = getNextSpiralNumber(
            SpiralNumber(3, 1, 1),
            setOf(
                SpiralNumber(1, 0, 0),
                SpiralNumber(2, 1, 0)
            )
        )
        assertEquals(SpiralNumber(4, 0, 1), nextCoordinate)
    }

    @Test
    fun test_getNextSpiralNumber_goesDown_ifNoBottomNeighbour_butRightNeighbour() {
        val nextCoordinate = getNextSpiralNumber(
            SpiralNumber(5, -1, 1),
            setOf(
                SpiralNumber(4, 0, 1)
            )
        )
        assertEquals(SpiralNumber(6, -1, 0), nextCoordinate)
    }


    @Test
    fun test_getNextSpiralNumber_goesRight_ifNoRightNeighbour_butTopNeighbour() {
        val nextCoordinate = getNextSpiralNumber(
            SpiralNumber(7, -1, -1),
            setOf(
                SpiralNumber(6, -1, 0)
            )
        )
        assertEquals(SpiralNumber(8, 0, -1), nextCoordinate)
    }

    @Test
    fun test_getManhattanDistanceToOrigin() {
        assertEquals(0, getManhattanDistanceToOrigin(SpiralNumber(1, 0, 0)))
        assertEquals(3, getManhattanDistanceToOrigin(SpiralNumber(12, 2, 1)))
        assertEquals(2, getManhattanDistanceToOrigin(SpiralNumber(23, 0, -2)))
    }

    @Test
    fun test_getSumOfSpiralNumberNeigbourValues() {
        assertEquals(
            2, getSumOfSpiralNumberNeigbourValues(
                SpiralNumber(3, 1, 1),
                mapOf(
                    Pair(SpiralNumber(1, 0, 0, 1), SpiralNumber(1, 0, 0, 1)),
                    Pair(SpiralNumber(2, 1, 0, 1), SpiralNumber(2, 1, 0, 1))
                )
            )
        )
        assertEquals(
            4, getSumOfSpiralNumberNeigbourValues(
                SpiralNumber(4, 0, 1),
                mapOf(
                    Pair(SpiralNumber(1, 0, 0, 1), SpiralNumber(1, 0, 0, 1)),
                    Pair(SpiralNumber(2, 1, 0, 1), SpiralNumber(2, 1, 0, 1)),
                    Pair(SpiralNumber(3, 1, 1, 2), SpiralNumber(3, 1, 1, 2))
                )
            )
        )
    }
}