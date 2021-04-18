package chapter6

fun breathFirstSearch(start: String, graph: Map<String, List<String>>, predicate: (String) -> Boolean): String? {
    val queue = Queue<String>()
    queue.pushAll(graph[start].orEmpty())

    while (queue.isNotEmpty()) {
        val neighbor = queue.pop()
        if (predicate(neighbor)) {
            return neighbor
        } else {
            queue.pushAll(graph[neighbor].orEmpty())
        }
    }

    return null
}

fun main(args: Array<String>) {
    val graph = mapOf(
        "you" to listOf("alice", "bob", "claire"),
        "bob" to listOf("anuj", "peggy"),
        "alice" to listOf("peggy"),
        "claire" to listOf("thom", "jonny"),
        "anuj" to emptyList(),
        "peggy" to emptyList(),
        "thom" to emptyList(),
        "jonny" to emptyList()
    )
    print("expected: thom, actual: ${breathFirstSearch("you", graph) { s -> s.last() == 'm' }}")
}