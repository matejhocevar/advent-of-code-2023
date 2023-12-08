data class PartNumber(
        val value: Int,
        val row: Int,
        val startOffset: Int,
        val endOffset: Int
)

fun main() {
    fun getNumbersFrom(input: List<String>): List<PartNumber> {
        val partNumbers = mutableListOf<PartNumber>()

        fun addPartNumber(value: String, row: Int, offset: Int) {
            partNumbers.add(PartNumber(value = value.toInt(), row = row, startOffset = offset, endOffset = offset + value.length - 1))

        }

        for (y in input.indices) {
            val row = input[y]

            var strBuffer = ""
            var offset = 0
            for (i in row.indices) {
                val char = row[i]
                if (char.isDigit()) {
                    strBuffer += char;
                } else {
                    if (strBuffer.isNotEmpty()) {
                        addPartNumber(strBuffer, y, offset)
                    }
                    strBuffer = "";
                    offset = i + 1
                }
            }

            if (strBuffer.isNotEmpty()) {
                addPartNumber(strBuffer, y, offset)
            }
        }

        return partNumbers
    }

    fun part1(input: List<String>): Int {
        val partNumbers = getNumbersFrom(input);

        // Sum adjacent ones
        var sum = 0
        for (part in partNumbers) {
            var isAdjacent = false
            for (row in (-1 + part.row)..(part.row + 1)) {
                for (col in (-1 + part.startOffset)..(part.endOffset + 1)) {
                    if (row > -1 && row < input.size && col > -1 && col < input[0].length) {
                        val char = input[row][col]
                        if (!char.isDigit() && char != '.') {
                            isAdjacent = true
                            break
                        }
                    }
                }

                if (isAdjacent) break
            }

            if (isAdjacent) {
                sum += part.value
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val partNumbers = getNumbersFrom(input);

        val gears = mutableMapOf<String, List<Int>>()
        for (part in partNumbers) {
            for (row in (-1 + part.row)..(part.row + 1)) {
                for (col in (-1 + part.startOffset)..(part.endOffset + 1)) {
                    if (row > -1 && row < input.size && col > -1 && col < input[0].length) {
                        val char = input[row][col]
                        if (char == '*') {
                            val key = "$row,$col"
                            if (!gears.containsKey(key)) {
                                gears[key] = mutableListOf()
                            }
                            (gears[key] as MutableList<Int>).add(part.value)
                        }
                    }
                }
            }
        }

        return gears.values.filter { it.size > 1 }.sumBy { list -> list.fold(1) { acc, num -> acc * num } }
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
