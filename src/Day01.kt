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
        val sum = ArrayList<Int>()
        var tempData = 0

        input.map {
            if (it.isNotEmpty()) {
                tempData += it.toInt()
            } else {
                sum.add(tempData)
                tempData = 0
            }
        }
        sum.sortDescending()
        return sum.take(3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
