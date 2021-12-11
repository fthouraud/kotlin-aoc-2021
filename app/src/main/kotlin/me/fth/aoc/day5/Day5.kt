package me.fth.aoc.day5

import me.fth.aoc.Line
import me.fth.aoc.Point
import me.fth.aoc.readInputLines
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

const val diagramSize = 999

fun resolvePartOne() = readInputLines(5) {
    val diagram = initDiagram()
    parseLines(it).filter { line -> line.isHorizontal() || line.isVertical() }.forEach { line ->
        line.getPoints().forEach { p -> diagram[p.y][p.x]++ }
    }
    diagram.forEach { row -> println(row.joinToString(" ")) }
    diagram.sumOf { row -> row.count { v -> v >= 2 } }
}

fun initDiagram() = Array(diagramSize) { IntArray(diagramSize) { 0 } }

fun parseLines(input: Sequence<String>): Sequence<Line> {
    val pointRegex = """(\d+),(\d+)""".toRegex()
    return input.flatMap { it.split(" -> ") }
        .map { pointRegex.matchEntire(it)?.destructured?.let { (x, y) -> Point(x.toInt(), y.toInt()) } }
        .filterNotNull()
        .windowed(2, 2) { (a, b) -> Line(a, b) }
}

fun Line.getPoints() = when {
    isDiagonal() -> when {
        a.x == a.y && b.x == b.y -> getRange(a.x, b.x).map { Point(it, it) }
        a.x == b.y && a.y == b.x -> {
            val diff = (a.x - a.y).absoluteValue
            getRange(0, diff).map { Point(min(a.x, a.y) + it, max(b.x, b.y) - it) }
        }
        a.x == a.y || b.x == b.y -> {
            if (a.x == a.y) {
                if (b.x > b.y) {
                    val diff = (b.x - a.x).absoluteValue
                    getRange(0, diff).map { Point(a.x + it, a.x - it) }
                } else {
                    val diff = (b.y - a.y).absoluteValue
                    getRange(0, diff).map { Point(a.x - it, a.x + it) }
                }
            } else {
                if (a.x > a.y) {
                    val diff = (a.x - b.x).absoluteValue
                    getRange(0, diff).map { Point(b.x + it, b.x - it) }
                } else {
                    val diff = (a.y - b.y).absoluteValue
                    getRange(0, diff).map { Point(b.x - it, b.x + it) }
                }
            }
        }
        else -> {
            val xRange = getRange(a.x, b.x).iterator()
            getRange(a.y, b.y).map { y -> Point(xRange.next(), y) }
        }
    }
    isHorizontal() -> getRange(a.y, b.y).map { Point(a.x, it) }
    isVertical() -> getRange(a.x, b.x).map { Point(it, a.y) }
    else -> emptyList()
}

fun Line.isHorizontal() = a.x == b.x

fun Line.isVertical() = a.y == b.y

fun getRange(a: Int, b: Int) = if (a > b) b..a else a..b

fun resolvePartTwo() = readInputLines(5) {
    val diagram = initDiagram()
    parseLines(it).forEach { line ->
        line.getPoints().forEach { p -> diagram[p.y][p.x]++ }
    }
    diagram.forEach { row -> println(row.joinToString(" ")) }
    diagram.sumOf { row -> row.count { v -> v >= 2 } }
}

fun Line.isDiagonal() = !(isHorizontal() || isVertical())
