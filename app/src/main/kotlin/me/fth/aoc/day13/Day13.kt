package me.fth.aoc.day13

import me.fth.aoc.Point
import me.fth.aoc.readInputLines
import kotlin.math.abs

typealias Matrix = Array<CharArray>

data class FoldInstruction(val on: Char, val value: Int)

fun resolvePartOne() = readInputLines(13) { raw ->
    val lines = raw.toList()

    initMatrix(lines).fold(parseFoldInstructions(lines).first())
        .sumOf { row -> row.count { it == '#' } }
}

fun initMatrix(lines: List<String>): Matrix {
    val points = parsePoints(lines)
    val maxX = points.maxOf(Point::x) + 1
    val maxY = points.maxOf(Point::y) + 1

    return with(Array(maxY) { CharArray(maxX) { '.' } }) {
        points.forEach { (x, y) -> this[y][x] = '#' }
        this
    }
}

fun parsePoints(lines: List<String>) = lines.takeWhile { !it.startsWith("fold") && it.isNotEmpty() }
    .map { it.split(",").let { split -> Point(split[0].toInt(), split[1].toInt()) } }

val foldInstructionRegex = """fold along ([xy])=(\d+)""".toRegex()

fun parseFoldInstructions(lines: List<String>) =
    lines.takeLastWhile { it.startsWith("fold") }
        .mapNotNull { foldInstructionRegex.matchEntire(it)?.destructured }
        .map { (on, value) -> FoldInstruction(on.first(), value.toInt()) }

fun Matrix.fold(foldInstruction: FoldInstruction): Matrix =
    if (foldInstruction.on == 'x') foldOnX(foldInstruction.value) else foldOnY(foldInstruction.value)

fun Matrix.foldOnY(from: Int): Matrix {
    val foldedMatrix = copyOfRange(0, from)
    val maxIndexOnFoldingLine = from * 2
    (maxIndexOnFoldingLine downTo from + 1).forEach { y ->
        val oppositeY = abs(y - maxIndexOnFoldingLine)
        this[y].indices.forEach { x -> if (this[y][x] == '#') foldedMatrix[oppositeY][x] = '#' }
    }
    return foldedMatrix
}

fun Matrix.foldOnX(from: Int): Matrix {
    val foldedMatrix = Array(size) { this[it].copyOfRange(0, from) }
    val maxIndexOnFoldingLine = from * 2
    indices.forEach { y ->
        (maxIndexOnFoldingLine downTo from + 1).forEach { x ->
            val oppositeX = abs(x - maxIndexOnFoldingLine)
            if (this[y][x] == '#') foldedMatrix[y][oppositeX] = '#'
        }
    }
    return foldedMatrix
}

fun printMatrix(matrix: Array<CharArray>) = matrix.onEach { row ->
    row.onEach { print(""" $it """) }
    println()
}

fun resolvePartTwo() = readInputLines(13) { raw ->
    val lines = raw.toList()

    parseFoldInstructions(lines).fold(initMatrix(lines)) { matrix, foldInstruction ->
        matrix.fold(foldInstruction)
    }.also { printMatrix(it) }
}
