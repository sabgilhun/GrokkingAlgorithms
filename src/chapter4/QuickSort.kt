package chapter4

private fun quickSort(list: List<Int>): List<Int> {
    if (list.size < 2) {
        return list
    }
    val pivot = list[0]
    val less = list.filter { it < pivot }
    val greater = list.filter { it > pivot }

    return quickSort(less) + pivot + quickSort(greater)
}

fun main() {
    val testSet1 = listOf(19, 23, 11, 5, 6)
    val testSet2 = listOf(1, 2, 3, 4, 19, 18)
    println("expected: {5, 6, 11, 19, 23}, actual: ${quickSort(testSet1)}")
    println("expected: {1, 2, 3, 4, 18, 19}, actual: ${quickSort(testSet2)}")
}