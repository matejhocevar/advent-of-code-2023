import kotlin.math.pow

fun main() {
    fun parseNumbers(str: String): List<Int> {
        return str
                .split(' ')
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
    }

    fun parseCard(line: String): Int {
        val data = line.split(':')[1].split('|')
        val winning = parseNumbers(data[0]).toSet()
        val numbers = parseNumbers(data[1]).toSet()

        return winning.intersect(numbers).size
    }

    fun calcMatches(input: List<String>): List<Int> {
        return input.map { parseCard(it) }
    }

    fun part1(input: List<String>): Int {
        val base = 2.0
        val matches = calcMatches(input)
        return matches.sumOf { base.pow(it - 1).toInt() }
    }

    fun part2(input: List<String>): Int {
        val matches = calcMatches(input)
        val copies = IntArray(matches.size) { 1 }
        matches.forEachIndexed { i, m ->
            repeat(m) {
                copies[i + it + 1] += copies[i]
            }
        }

        return copies.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
