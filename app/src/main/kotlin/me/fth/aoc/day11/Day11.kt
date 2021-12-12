package me.fth.aoc.day11

import me.fth.aoc.Point
import me.fth.aoc.readInputLines
import java.util.concurrent.atomic.AtomicInteger

typealias OctopusPowerMatrix = Array<IntArray>

fun resolvePartOne() = readInputLines(11) { lines ->
    val matrix = parseMatrix(lines)
    val flashCounter = AtomicInteger()
    repeat(100) {
        matrix.increaseEachByOne()
        matrix.triggerFlashIfPossible().onEach { (x, y) -> matrix[y][x] = 0 }.also {
            flashCounter.getAndAdd(it.size)
        }
    }
    flashCounter.get()
}

fun parseMatrix(lines: Sequence<String>) = lines.toList().map { it.map(Char::digitToInt).toIntArray() }.toTypedArray()

fun OctopusPowerMatrix.increaseEachByOne() = indices.forEach { y -> this[y].indices.forEach { x -> this[y][x] += 1 } }

fun OctopusPowerMatrix.triggerFlashIfPossible(): Set<Point> = buildSet flashPositions@{
    indices.forEach { y ->
        this@triggerFlashIfPossible[y].indices.forEach { x ->
            triggerOctopusFlashIfPossible(this@triggerFlashIfPossible, Point(x, y), this@flashPositions)
        }
    }
}

fun triggerOctopusFlashIfPossible(
    matrix: OctopusPowerMatrix, position: Point, octopusPositionsThatFlashed: MutableSet<Point>
) {
    val (x, y) = position
    if (matrix[y][x] > 9 && position !in octopusPositionsThatFlashed) {
        octopusPositionsThatFlashed += position
        matrix.getAdjacentPositions(position).onEach { (x, y) -> matrix[y][x] += 1 }
            .forEach { p -> triggerOctopusFlashIfPossible(matrix, p, octopusPositionsThatFlashed) }
    }
}

fun OctopusPowerMatrix.getAdjacentPositions(point: Point): List<Point> {
    val (x, y) = point
    val rowRange = 0 until this[0].size
    val colRange = 0 until this.size
    return buildList {
        ((y - 1)..(y + 1)).forEach { y ->
            ((x - 1)..(x + 1)).forEach { x ->
                if (x in rowRange && y in colRange) add(Point(x, y))
            }
        }
        remove(point)
    }
}

const val matrixSize = 100

fun resolvePartTwo() = readInputLines(11) { lines ->
    val matrix = parseMatrix(lines)
    repeat(500) { step ->
        matrix.increaseEachByOne()
        matrix.triggerFlashIfPossible().onEach { (x, y) -> matrix[y][x] = 0 }.also {
            if (it.size == matrixSize) return@readInputLines step + 1
        }
    }
}
