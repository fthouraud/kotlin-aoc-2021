package me.fth.aoc

fun <T> readInputLines(dayNumber: Int, block: (Sequence<String>) -> T): T =
    object {}.javaClass.getResourceAsStream("""/day$dayNumber.txt""")!!.bufferedReader().useLines(block)

data class Point(val x: Int, val y: Int)
data class Line(val a: Point, val b: Point)
