package chapter1

private fun binarySearch(array: IntArray, target: Int): Int? {
    var low = 0
    var high = array.size - 1
    while (low <= high) {
        val mid = (low + high) / 2
        val guess = array[mid]

        when {
            guess == target -> return mid
            guess > target -> high = mid - 1
            else -> low = mid + 1
        }
    }
    return null
}

fun main() {
    val testSet = intArrayOf(1, 3, 9, 10, 11, 15, 19)
    println("expected: 2, actual: ${binarySearch(testSet, 9)}")
    println("expected: null, actual: ${binarySearch(testSet, 12)}")
}