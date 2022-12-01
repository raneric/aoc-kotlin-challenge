fun main() {
    fun part1(input: List<String>): Int {
        var higherCal = Int.MIN_VALUE
        var tempData = 0

        input.map {
            if (it.isNotEmpty()) {
                tempData += it.toInt()
            } else {
                higherCal = higherCal.coerceAtLeast(tempData)
                tempData = 0
            }
        }
        return higherCal
    }

    fun part2(input: List<String>): Int {
        var first = Int.MIN_VALUE
        var second = Int.MIN_VALUE
        var third = Int.MIN_VALUE

        var tempData = 0

        input.map {
            if (it.isNotEmpty()) {
                tempData += it.toInt()
            } else {
                if (first < tempData) {
                    first = tempData
                } else if (second < tempData) {
                    second = tempData
                } else if (third < tempData) {
                    third = tempData
                }
                tempData = 0
            }
        }
        return first + second + third
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
