var stackSourceIndex = -1
var stackDestinationIndex = -1
fun main() {

    fun part1(input: List<String>): String {
        val stackList = getStackList(input)
        val moveIndex = stackList.size + 2
        val procedure = getMoveList(moveIndex, input)
        val stackNb = input[moveIndex - 2].trim().split(" ").last().toInt()
        val reversedData = transpose(stackList, stackNb)



        for (move in procedure) {
            stackSourceIndex = move[1] - 1
            stackDestinationIndex = move[2] - 1
            for (i in 1..move[0]) {
                reversedData[stackDestinationIndex].add(0, reversedData[stackSourceIndex].first())
                reversedData[stackSourceIndex].removeFirst()
            }
        }

        return reversedData.joinToString(separator = "") { it.first().trim().removeBracket() }
    }

    fun part2(input: List<String>): String {
        val stackList = getStackList(input)
        val moveIndex = stackList.size + 2
        val procedure = getMoveList(moveIndex, input)
        val stackNb = input[moveIndex - 2].trim().split(" ").last().toInt()
        val reversedData = transpose(stackList, stackNb)

        for (move in procedure) {
            stackSourceIndex = move[1] - 1
            stackDestinationIndex = move[2] - 1
            val stackToMove = reversedData[stackSourceIndex].take(move[0])
            reversedData[stackDestinationIndex].addAll(0, stackToMove)
            for (i in stackToMove.indices) {
                reversedData[stackSourceIndex].removeFirst()
            }
        }
        return reversedData.joinToString(separator = "") { it.first().trim().removeBracket() }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun getStackList(input: List<String>): ArrayList<ArrayList<String>> {
    val stackList = ArrayList<ArrayList<String>>()
    for (i in input.indices) {
        if (input[i].startsWith(" 1 ")) {
            break
        }
        stackList.add(input[i].extraDataToArray())
    }
    return stackList
}

fun String.extraDataToArray(): ArrayList<String> {
    return this.chunked(4) as ArrayList<String>
}

fun getMoveList(moveIndex: Int, input: List<String>): List<List<Int>> {
    return input.subList(moveIndex, input.size).map {
        it.extractProcedure()
    }
}

fun String.extractProcedure(): List<Int> {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    val result = this.split(" ").filter { regex.matches(it) }.map { it.toInt() }
    return result
}

fun transpose(stackSource: ArrayList<ArrayList<String>>, width: Int): ArrayList<ArrayList<String>> {
    val transposed = ArrayList<ArrayList<String>>()
    for (i in 0 until width) {
        val tempData = ArrayList<String>()
        for (j in stackSource.indices) {
            if (i == stackSource[j].size) continue
            if (stackSource[j][i].startsWith("  ")) {
                continue
            } else {
                tempData.add(stackSource[j][i])
            }
        }
        transposed.add(tempData)
    }
    return transposed
}

fun String.removeBracket(): String {
    return this.replace("[", "").replace("]", "")
}