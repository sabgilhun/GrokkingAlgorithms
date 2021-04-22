package chapter9

fun dynamicPrograming(availableAmount: Int, itemValues: List<Pair<Int, Int>>): Int {

    val twoDimensionArray = Array(itemValues.size) { IntArray(availableAmount) }

    fun storedValue(i: Int, j: Int) = twoDimensionArray.getOrNull(i)?.getOrNull(j) ?: 0

    fun calcM1(i: Int, j: Int): Int {
        var max = 0
        for (_i in 0 until i) {
            for (_j in 0..j) {
                val cur = twoDimensionArray[_i][_j]
                if (cur > max) {
                    max = cur
                }
            }
        }
        return max
    }

    fun calcM2(i: Int, j: Int): Int {
        val currentItemWeight = itemValues[i].first
        val currentItemValue = itemValues[i].second
        val remainSpace = j + 1 - currentItemWeight

        return when {
            remainSpace < 0 -> 0
            remainSpace == 0 -> currentItemValue
            else -> currentItemValue + storedValue(i - 1, remainSpace - 1)
        }
    }

    for (i in twoDimensionArray.indices) {
        for (j in twoDimensionArray[i].indices) {
            val m1 = calcM1(i, j)
            val m2 = calcM2(i, j)
            twoDimensionArray[i][j] = if (m1 > m2) m1 else m2
        }
    }

    return twoDimensionArray.last().last()
}

fun main(args: Array<String>) {
    val values = listOf(
        4 to 300,
        3 to 200,
        1 to 150
    )
    print("expected: 350, actual: ${dynamicPrograming(4, values)}")
}
