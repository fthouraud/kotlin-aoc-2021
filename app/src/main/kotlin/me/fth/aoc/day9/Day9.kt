package me.fth.aoc.day9

import me.fth.aoc.Point
import me.fth.aoc.readInputLines

typealias Matrix = List<List<Int>>
typealias Basin = Set<Point>

fun resolvePartOne(): Int = readInputLines(9) { lines ->
    with(parseMatrix(lines)) { getLowestPoints().sumOf { this[it.y][it.x] + 1 } }
}

fun parseMatrix(lines: Sequence<String>) = lines.toList().map { line -> line.toList().map(Char::digitToInt) }

fun Matrix.getLowestPoints(): List<Point> {
    val matrix = this
    return buildList<Point> {
        matrix.indices.forEach { y ->
            matrix[y].indices.forEach { x ->
                val point = Point(x, y)
                if (getAdjacentValues(point).all { p -> matrix[y][x] < matrix[p.y][p.x] }) add(point)
            }
        }
    }
}

fun Matrix.getAdjacentValues(point: Point): List<Point> {
    val (x, y) = point
    val rowsCount = this[0].size
    val colsCount = this.size
    return buildList {
        if (x - 1 >= 0) add(Point(x - 1, y))
        if (x + 1 < rowsCount) add(Point(x + 1, y))
        if (y - 1 >= 0) add(Point(x, y - 1))
        if (y + 1 < colsCount) add(Point(x, y + 1))
    }
}

fun resolvePartTwo() = readInputLines(9) { lines ->
    val pointsInsBasin = mutableSetOf<Point>()
    with(parseMatrix(lines)) {
        getLowestPoints().map { point -> collectAdjacentElevatedPoints(this, point, pointsInsBasin) }
            .sortedBy(Basin::size)
            .takeLast(3).fold(1) { total, basin -> total * basin.size }
    }
}

fun collectAdjacentElevatedPoints(matrix: Matrix, point: Point, pointsInsBasin: MutableSet<Point>): Basin {
    val value = matrix[point.y][point.x]

    pointsInsBasin.add(point)

    return matrix.getAdjacentValues(point)
        .filterNot(pointsInsBasin::contains)
        .filter { matrix[it.y][it.x] >= value && matrix[it.y][it.x] != 9 }
        .fold<Point, MutableSet<Point>>(mutableSetOf()) { basin, p ->
            basin.addAll(collectAdjacentElevatedPoints(matrix, p, pointsInsBasin))
            basin
        }.also { it.add(point) }
}
