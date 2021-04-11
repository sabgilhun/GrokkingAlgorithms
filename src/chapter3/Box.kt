package chapter3

data class Box(
    val id: String,
    val haveKey: Boolean,
    val innerBoxes: List<Box>
)

fun generateTestSet() = Box(
    id = "1",
    haveKey = false,
    innerBoxes = listOf(
        Box(
            id = "2",
            haveKey = false,
            innerBoxes = emptyList()
        ),
        Box(
            id = "3",
            haveKey = false,
            innerBoxes = listOf(
                Box(
                    id = "5",
                    haveKey = false,
                    innerBoxes = emptyList()
                ),
                Box(
                    id = "6",
                    haveKey = false,
                    innerBoxes = emptyList()
                ),
                Box(
                    id = "7",
                    haveKey = true,
                    innerBoxes = emptyList()
                )
            )
        ),
        Box(
            id = "4",
            haveKey = false,
            innerBoxes = listOf(
                Box(
                    id = "8",
                    haveKey = false,
                    innerBoxes = emptyList()
                ),
                Box(
                    id = "9",
                    haveKey = false,
                    innerBoxes = emptyList()
                ),
                Box(
                    id = "10",
                    haveKey = false,
                    innerBoxes = emptyList()
                )
            )
        )
    )
)