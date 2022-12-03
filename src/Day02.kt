const val WIN_SCORE = 6
const val DRAW_SCORE = 3
const val ROCK_SCORE = 1
const val PAPER_SCORE = 2
const val SCISSOR_SCORE = 3

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { it.checkScore() }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it.simulateGameWin() }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun String.checkScore(): Int {
    return this[2].checkIfWin(this[0])
}

fun Char.checkIfWin(you: Char): Int {
    var result = 0
    when (this) {
        'X' -> {
            result = ROCK_SCORE
            if (you == 'C') {
                result += WIN_SCORE
            } else if (you == 'A') {
                result += DRAW_SCORE
            }
        }

        'Y' -> {
            result = PAPER_SCORE
            if (you == 'A') {
                result += WIN_SCORE
            } else if (you == 'B') {
                result += DRAW_SCORE
            }
        }

        'Z' -> {
            result = SCISSOR_SCORE
            if (you == 'B') {
                result += WIN_SCORE
            } else if (you == 'C') {
                result += DRAW_SCORE
            }
        }
    }
    return result
}

fun String.simulateGameWin(): Int {
    return this[0].choose(this[2])
}

fun Char.choose(end: Char): Int {
    return when (end) {
        'X' -> this.loose()
        'Y' -> this.draw()
        'Z' -> this.win()
        else -> 0
    }
}

fun Char.loose(): Int {
    return when (this) {
        'A' -> SCISSOR_SCORE
        'B' -> ROCK_SCORE
        'C' -> PAPER_SCORE
        else -> 0
    }
}

fun Char.draw(): Int {
    return DRAW_SCORE + when (this) {
        'A' -> ROCK_SCORE
        'B' -> PAPER_SCORE
        'C' -> SCISSOR_SCORE
        else -> 0
    }
}

fun Char.win(): Int {
    return WIN_SCORE + when (this) {
        'A' -> PAPER_SCORE
        'B' -> SCISSOR_SCORE
        'C' -> ROCK_SCORE
        else -> 0
    }
}