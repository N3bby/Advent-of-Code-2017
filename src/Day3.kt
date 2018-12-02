//Afterthought:
//This has become quite a mess...

data class SpiralNumber(val number: Int, val x: Int, val y: Int, var sumOfNeighboursAtGeneration: Int = 0) {
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is SpiralNumber) {
            false
        } else {
            return x == other.x && y == other.y
        }
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

operator fun SpiralNumber.plus(v: Vector2): SpiralNumber {
    return SpiralNumber(number, x + v.x, y + v.y)
}

operator fun SpiralNumber.minus(v: Vector2): SpiralNumber {
    return SpiralNumber(number, x - v.x, y - v.y)
}

operator fun SpiralNumber.plus(i: Int): SpiralNumber {
    return SpiralNumber(number + i, x, y)
}

data class Vector2(val x: Int, val y: Int) {
    companion object {
        val left = Vector2(-1, 0)
        val right = Vector2(1, 0)
        val up = Vector2(0, 1)
        val down = Vector2(0, -1)
    }
}

fun main(vararg args: String) {
    val number = 347991
    val spiralNumbers = generateSpiralNumbers(number)
    val spiralNumber = spiralNumbers.find { spiralNumber -> spiralNumber.number == number }
    println("Steps needed to carry data: ${getManhattanDistanceToOrigin(spiralNumber!!)}")
}

fun getManhattanDistanceToOrigin(spiralNumber: SpiralNumber): Int {
    return Math.abs(spiralNumber.x) + Math.abs(spiralNumber.y)
}

fun generateSpiralNumbers(to: Int): Set<SpiralNumber> {
    if (to < 2) {
        throw IllegalArgumentException("Unable to create a number spiral of less than two numbers")
    }

    //Fuck it... not rewriting my model again for part 2
    var currentSpiralNumber = SpiralNumber(2, 1, 0, 1)
    val spiralNumbers = mutableMapOf(
        Pair(SpiralNumber(1, 0, 0, 1), SpiralNumber(1, 0, 0, 1)),
        Pair(currentSpiralNumber, currentSpiralNumber)
    )

    var foundSumOfNeighboursValueExceedingInput = false

    while (currentSpiralNumber.number <= to) {
        currentSpiralNumber = getNextSpiralNumber(currentSpiralNumber, spiralNumbers.keys)
        currentSpiralNumber.sumOfNeighboursAtGeneration = getSumOfSpiralNumberNeigbourValues(currentSpiralNumber, spiralNumbers)
        if(!foundSumOfNeighboursValueExceedingInput && currentSpiralNumber.sumOfNeighboursAtGeneration > to) {
            println("sumOfNeighbours value exceeded puzzle input: ${currentSpiralNumber.sumOfNeighboursAtGeneration}")
            foundSumOfNeighboursValueExceedingInput = true
        }
        spiralNumbers[currentSpiralNumber] = currentSpiralNumber
    }

    return spiralNumbers.keys
}

fun getSumOfSpiralNumberNeigbourValues(
    currentSpiralNumber: SpiralNumber,
    allSpiralNumbers: Map<SpiralNumber, SpiralNumber>
): Int {
    return listOf(
        allSpiralNumbers[currentSpiralNumber + Vector2.right],
        allSpiralNumbers[currentSpiralNumber + Vector2.right + Vector2.up],
        allSpiralNumbers[currentSpiralNumber + Vector2.up],
        allSpiralNumbers[currentSpiralNumber + Vector2.up + Vector2.left],
        allSpiralNumbers[currentSpiralNumber + Vector2.left],
        allSpiralNumbers[currentSpiralNumber + Vector2.left + Vector2.down],
        allSpiralNumbers[currentSpiralNumber + Vector2.down],
        allSpiralNumbers[currentSpiralNumber + Vector2.down + Vector2.right]
    ).stream()
        .filter { spiralNumber -> spiralNumber != null }
        .mapToInt { value -> value!!.sumOfNeighboursAtGeneration }
        .sum()
}

fun getNextSpiralNumber(currentSpiralNumber: SpiralNumber, allSpiralNumbers: Set<SpiralNumber>): SpiralNumber {
    val hasLeftNeighbour = allSpiralNumbers.contains(currentSpiralNumber + Vector2.left)
    val hasRightNeighbour = allSpiralNumbers.contains(currentSpiralNumber + Vector2.right)
    val hasTopNeighbour = allSpiralNumbers.contains(currentSpiralNumber + Vector2.up)
    val hasBottomNeighbour = allSpiralNumbers.contains(currentSpiralNumber + Vector2.down)

    if (!hasTopNeighbour && hasLeftNeighbour) {
        return currentSpiralNumber + Vector2.up + 1
    }
    if (!hasLeftNeighbour && hasBottomNeighbour) {
        return currentSpiralNumber + Vector2.left + 1
    }
    if (!hasBottomNeighbour && hasRightNeighbour) {
        return currentSpiralNumber + Vector2.down + 1
    }
    if (!hasRightNeighbour && hasTopNeighbour) {
        return currentSpiralNumber + Vector2.right + 1
    }

    throw IllegalArgumentException("Unable to determine next coordinate")
}
