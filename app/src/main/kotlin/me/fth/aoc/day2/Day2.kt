package me.fth.aoc.day2

import me.fth.aoc.readInputLines

interface UpdatableLocation {
    fun dive(depth: Int)
    fun rise(depth: Int)
    fun progress(distance: Int)
    val value: Int
}

fun calculateLocation(location: UpdatableLocation): Int = readInputLines(2) { lines ->
    lines.fold(location) { location, command ->
        val (instruction, value) = command.split(" ")
        when (instruction) {
            "forward" -> location.progress(value.toInt())
            "down" -> location.dive(value.toInt())
            "up" -> location.rise(value.toInt())
        }
        location
    }.value
}

fun resolvePartOne(): Int = calculateLocation(Location)

fun resolvePartTwo(): Int = calculateLocation(AimedLocation)
