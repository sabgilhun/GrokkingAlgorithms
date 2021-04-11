package chapter3

fun findKeyWithRecursion(box: Box): Box {
    // Base Case
    if (box.haveKey) {
        return box
    }

    // Recursion Case
    box.innerBoxes.forEach {
        findKeyWithRecursion(it)
    }

    throw IllegalArgumentException("No box having key")
}

fun main(args: Array<String>) {
    val testSet = generateTestSet()
    println("expected: 7, actual: ${findKeyWithIteration(testSet).id}")
}
