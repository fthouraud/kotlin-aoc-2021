package me.fth.aoc.day1

import me.fth.aoc.readInputLines

fun resolvePartOne(): Int = readInputLines(1) { lines ->
    lines.zipWithNext { a, b -> if (a.toInt() < b.toInt()) 1 else 0 }.sum()
}

fun resolvePartTwo(): Int = readInputLines(1) { lines ->
    lines.windowed(3) { it.sumOf(String::toInt) }.zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()
}
