fun main() {
    fun part1(input: List<String>): Int {
        val receivedData = input[0]
        return receivedData.getNoDuplicatedStartIndex(4)
    }

    fun part2(input: List<String>): Int {
        val startIndex = part1(input)
        val receivedData = input[0]
        return receivedData.getNoDuplicatedStartIndex(14, startIndex)
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}


fun String.getNoDuplicatedStartIndex(nbOfChar: Int, startIndex: Int = 0): Int {
    var index = -1
    for (i in startIndex until this.length) {
        if (this.substring(i).take(nbOfChar).isDuplicated()) {
            continue
        } else {
            index = i + nbOfChar
            break
        }
    }
    return index
}

fun String.isDuplicated(): Boolean {
    val distinct = this.toSet()
    return distinct.size != this.length
}