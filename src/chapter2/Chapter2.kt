package chapter2


private fun selectionSort(array: IntArray): List<Int> {
    val inputList = array.toMutableList()

    fun findSmallest(): Int {
        var smallest = inputList[0]
        var index = 0
        for (i in 1 until inputList.size) {
            if (inputList[i] < smallest) {
                smallest = inputList[i]
                index = i
            }
        }
        return index
    }

    val list = mutableListOf<Int>()
    while (inputList.isNotEmpty()) {
        val index = findSmallest()
        list.add(inputList.removeAt(index))
    }

    return list
}

fun main() {
    val testSet1 = intArrayOf(19, 23, 11, 5, 6)
    val testSet2 = intArrayOf(1, 2, 3, 4, 19, 18)
    println("expected: {5, 6, 11, 19, 23}, actual: ${selectionSort(testSet1)}")
    println("expected: {1, 2, 3, 4, 18, 19}, actual: ${selectionSort(testSet2)}")
}