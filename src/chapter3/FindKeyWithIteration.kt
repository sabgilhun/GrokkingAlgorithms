package chapter3


fun findKeyWithIteration(box: Box): Box {
    if (box.haveKey) {
        return box
    }

    val boxesToCheck = box.innerBoxes.toMutableList()
    while (boxesToCheck.isNotEmpty()) {
        val toCheck = boxesToCheck.removeAt(0)
        if (toCheck.haveKey) {
            return toCheck
        } else {
            boxesToCheck.addAll(toCheck.innerBoxes)
        }
    }
    throw IllegalArgumentException("No box having key")
}

fun main(args: Array<String>) {
    val testSet = generateTestSet()
    println("expected: 7, actual: ${findKeyWithIteration(testSet).id}")
}