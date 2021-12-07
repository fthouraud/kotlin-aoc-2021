package me.fth.aoc.day7

import me.fth.aoc.readInputLines
import kotlin.math.abs

fun resolvePartOne() = readInputLines(7) {
    findLowestFuelConsumption(it) { position, crabPosition -> getDistance(position, crabPosition) }
}

private fun findLowestFuelConsumption(it: Sequence<String>, fuelConsumptionFormula: (Int, Int) -> Int): Int? {
    val horizontalPositions = parseHorizontalPositions(it)
    val highestPosition = horizontalPositions.maxOrNull()!!
    val lowestPosition = horizontalPositions.minOrNull()!!

    return (lowestPosition..highestPosition).map { position ->
        horizontalPositions.sumOf { crabPosition -> fuelConsumptionFormula(position, crabPosition) }
    }.minOrNull()
}

fun parseHorizontalPositions(input: Sequence<String>) = input.first().split(",").map(String::toInt)

fun getDistance(position: Int, crabPosition: Int) = abs(position - crabPosition)

fun resolvePartTwo() = readInputLines(7) {
    findLowestFuelConsumption(it) { position, crabPosition -> (1..getDistance(position, crabPosition)).sum() }
}
