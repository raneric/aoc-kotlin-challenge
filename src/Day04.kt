fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.checkIfBetween() }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.checkIfOverlapp() }.sum()
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun String.checkIfBetween(): Int {
    val range = this.split(',')
    val firstRange = range[0].trim().split('-').map { it.toInt() }
    val secondRange = range[1].trim().split('-').map { it.toInt() }
    return if ((secondRange[0] in firstRange[0]..firstRange[1]
                && secondRange[1] in firstRange[0]..firstRange[1]) ||
        (firstRange[0] in secondRange[0]..secondRange[1]
                && firstRange[1] in secondRange[0]..secondRange[1])
    ) 1 else 0
}

fun String.checkIfOverlapp(): Int {
    val range = this.split(',')
    val firstRange = range[0].trim().split('-').map { it.toInt() }
    val secondRange = range[1].trim().split('-').map { it.toInt() }
    return if (secondRange[0] in firstRange[0]..firstRange[1]
        || secondRange[1] in firstRange[0]..firstRange[1] ||
        firstRange[0] in secondRange[0]..secondRange[1]
        || firstRange[1] in secondRange[0]..secondRange[1]
    ) 1 else 0
}