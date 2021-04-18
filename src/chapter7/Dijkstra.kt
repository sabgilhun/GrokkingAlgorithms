package chapter7


fun dijkstra(
    start: String,
    weightedGraph: Map<String, Map<String, Int>>,
    predicate: (String) -> Boolean
): Map.Entry<String, Int>? {
    val costTable = mutableMapOf<String, Int>()
    weightedGraph.forEach { (k, _) -> costTable[k] = Int.MAX_VALUE }
    var smallestCostNode = start
    costTable[smallestCostNode] = 0
    val visitedNode = mutableSetOf(start)
    while (visitedNode.size < weightedGraph.size) {
        val near = weightedGraph[smallestCostNode]!!
        val offset = costTable[smallestCostNode]!!
        near.forEach { (k, c) ->
            val previous = costTable[k]!!
            if (offset + c < previous) {
                costTable[k] = offset + c
            }
        }

        smallestCostNode = costTable
            .filter { (k, _) -> !visitedNode.contains(k) }
            .minBy { (_, v) -> v }
            ?.key ?: error("impossible")

        visitedNode.add(smallestCostNode)
    }
    return costTable.entries.find { (t, _) -> predicate(t) }
}

fun main(args: Array<String>) {
    val weightedGraph = mapOf(
        "악보" to mapOf("LP" to 5, "포스터" to 0),
        "LP" to mapOf("기타" to 15, "드럼" to 20),
        "포스터" to mapOf("기타" to 30, "드럼" to 35),
        "기타" to mapOf("피아노" to 20),
        "드럼" to mapOf("피아노" to 10),
        "피아노" to emptyMap()
    )

    print("expected: 35, actual: ${(dijkstra("악보", weightedGraph) { s -> s == "피아노" })?.value}")
}
