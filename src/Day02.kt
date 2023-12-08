fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        val cubes = mapOf(
                "red" to 12,
                "green" to 13,
                "blue" to 14
        )

        for (game in input) {
            var gameId = Regex("Game (\\d+):").find(game)?.groupValues?.get(1)?.toInt() ?: 0
            val sets = game
                    .split(":")[1]
                    .split(";")
                    .map { it.trim() }

            for (set in sets) {
                for (cube in set.split(", ")) {
                    val (count, color) = cube.split(" ")

                    if (count.toInt() > cubes[color] as Int) {
                        gameId = 0
                    }
                }
            }

            sum += gameId
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (game in input) {
            val mins = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0
            )

            val sets = game
                    .split(":")[1]
                    .split(";")
                    .map { it.trim() }

            for (set in sets) {
                for (cube in set.split(", ")) {
                    val (count, color) = cube.split(" ")
                    if (count.toInt() > mins[color] as Int) {
                        mins[color] = count.toInt()
                    }
                }
            }

            sum += mins.values.fold(1) { acc, num -> acc * num }
        }

        return sum
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
