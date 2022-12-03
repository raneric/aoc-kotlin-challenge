fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.splitAndCheckRec() }.flatten().sum()
    }

    fun part2(input: List<String>): Int {
        return input.findBadgeSum()
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun String.splitAndCheckRec(): List<Int> {
    return this.toCharArray().take(this.length / 2).filter {
        this.toCharArray().takeLast(this.length / 2).contains(it)
    }.toSet().map {
        it.convertAsciiCOde()
    }
}

fun List<String>.findBadgeSum(): Int {
    return this.asSequence().chunked(3).map { groupArray ->
        groupArray[0].filter {
            groupArray[1].contains(it) && groupArray[2].contains(it)
        }.toSet()
    }.flatten().map {
        it.convertAsciiCOde()
    }.sum()
}

fun Char.convertAsciiCOde(): Int {
    return this.code - if (this.isLowerCase()) 96 else 38
}