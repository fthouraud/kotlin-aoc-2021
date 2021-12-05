package me.fth.aoc.day4

import me.fth.aoc.readInputLines

typealias BoardRow = List<BoardCell>
typealias BingoBoard = List<BoardRow>

class BoardCell(val number: Int, var matched: Boolean = false)

fun resolvePartOne() = readInputLines(4) {
    val lines = it.toList()
    val drawnNumbers = initialiseDrawnNumbers(lines)
    val boards = initialiseBoard(lines)

    play(drawnNumbers, boards)
}

private fun initialiseBoard(lines: List<String>) = lines.drop(2).filterNot(String::isBlank)
    .windowed(5, 5) { rows ->
        rows.map { row -> row.trim().split("""[ ]+""".toRegex()).map { value -> BoardCell(value.trim().toInt()) } }
    }

private fun initialiseDrawnNumbers(lines: List<String>) =
    lines.first().split(',').map(String::toInt).asSequence()

private fun play(
    drawnNumbers: Sequence<Int>,
    boards: List<BingoBoard>
): Int {
    drawnNumbers.forEach { n ->
        boards.forEach { board ->
            board.forEachIndexed { rowIndex, row ->
                row.markMatchingCell(n)?.let { cell ->
                    if (board.hasWinningRowOrColumn(rowIndex, cell))
                        return@play board.getScore(n)
                }
            }
        }
    }
    return 0
}

fun BingoBoard.getScore(multiplier: Int) = sumOf { r ->
    r.filterNot(BoardCell::matched).sumOf(BoardCell::number)
} * multiplier

fun BoardRow.markMatchingCell(number: Int): BoardCell? =
    find { cell -> cell.number == number }?.let { cell -> cell.matched = true; cell }

fun resolvePartTwo() = readInputLines(4) {
    val lines = it.toList()
    val drawnNumbers = initialiseDrawnNumbers(lines)
    val boards = initialiseBoard(lines)

    playToLoose(drawnNumbers, boards)
}

private fun playToLoose(
    drawnNumbers: Sequence<Int>,
    boards: List<BingoBoard>
): Int {
    val completedBoards = mutableSetOf<Int>()
    drawnNumbers.forEach { n ->
        boards.forEachIndexed { boardIndex, board ->
            board.forEachIndexed { rowIndex, row ->
                row.markMatchingCell(n)?.let { cell ->
                    if (board.hasWinningRowOrColumn(rowIndex, cell)) {
                        completedBoards.add(boardIndex)
                        if (completedBoards.size == boards.size)
                            return@playToLoose board.getScore(n)
                    }
                }
            }
        }
    }
    return 0
}

private fun BingoBoard.hasWinningRowOrColumn(
    rowIndex: Int,
    cell: BoardCell
) = this[rowIndex].all(BoardCell::matched) || this.all { r -> r[this[rowIndex].indexOf(cell)].matched }
