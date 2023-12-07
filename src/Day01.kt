fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val num = line.replace(Regex("[A-Za-z]"), "")
            val first: Int = num.firstOrNull()?.digitToInt() ?: 0
            val last: Int = num.lastOrNull()?.digitToInt() ?: first
            sum += "$first$last".toInt()
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return part1(input.map {
            it
                    .replace("one", "o1e")
                    .replace("two", "t2o")
                    .replace("three", "t3e")
                    .replace("four", "f4r")
                    .replace("five", "f5e")
                    .replace("six", "s6x")
                    .replace("seven", "s7n")
                    .replace("eight", "e8t")
                    .replace("nine", "n9e")
        })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
