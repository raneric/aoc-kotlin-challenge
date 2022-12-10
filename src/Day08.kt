fun main() {
    fun part1(input: List<String>): Int {
        val listOfInt = input.convertToIntArray()
        return countVisibleTree(listOfInt)
    }

    fun part2(input: List<String>): Int {
        val listOfInt = input.convertToIntArray()
        return getHigherScore(listOfInt)
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

fun List<String>.convertToIntArray(): List<List<Int>> {
    val dataList = mutableListOf<List<Int>>()
    for (i in 0 until this.size) {
        dataList.add(this[i].map { it.digitToInt() })
    }
    return dataList
}

//----------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------PART 01-------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
fun countVisibleTree(list: List<List<Int>>): Int {
    var total = (list.size * 2) - 2 + (list[0].size * 2) - 2
    for (i in 1 until list.lastIndex) {
        for (j in 1 until list[i].lastIndex) {
            if (checkInLine(list, i, j) || checkInColumn(list, i, j)) {
                total++
            }
        }
    }
    return total
}

fun checkInLine(row: List<List<Int>>, i: Int, j: Int): Boolean {
    val left = row[i].subList(0, j).filter { it >= row[i][j] }
    val right = row[i].subList(j + 1, row.size).filter { it >= row[i][j] }
    return left.isEmpty() || right.isEmpty()
}

fun checkInColumn(list: List<List<Int>>, i: Int, j: Int): Boolean {
    val column = list.map { it[j] }
    val top = column.subList(0, i).filter { it >= list[i][j] }
    val bottom = column.subList(i + 1, list.size).filter { it >= list[i][j] }
    return top.isEmpty() || bottom.isEmpty()
}

//----------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------PART 2-------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------

fun getHigherScore(listOfInt: List<List<Int>>): Int {
    var higher = Int.MIN_VALUE
    var tempResult: Int
    for (i in 1 until listOfInt.lastIndex) {
        for (j in 1 until listOfInt[i].lastIndex) {
            tempResult = getHorizontalScore(listOfInt, i, j) * getVerticalScore(listOfInt, i, j)
            higher = higher.coerceAtLeast(tempResult)
        }
    }
    return higher
}

fun getHorizontalScore(row: List<List<Int>>, i: Int, j: Int): Int {
    val left = row[i].subList(0, j).reversed().countUntilHigher(row[i][j])
    val right = row[i].subList(j + 1, row.size).countUntilHigher(row[i][j])
    return left * right
}

fun getVerticalScore(data: List<List<Int>>, i: Int, j: Int): Int {
    val column = data.map { it[j] }
    val top = column.subList(0, i).reversed().countUntilHigher(data[i][j])
    val bottom = column.subList(i + 1, column.size).countUntilHigher(data[i][j])
    return top * bottom
}

private fun List<Int>.countUntilHigher(value: Int): Int {
    var count = 0
    for (data in this) {
        if (data <= value) {
            count++
            if (data == value) break
        } else {
            count++
            break
        }
    }
    return count
}
