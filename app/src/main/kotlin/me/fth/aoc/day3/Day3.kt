package me.fth.aoc.day3

import me.fth.aoc.readInputLines

fun resolvePartOne() = readInputLines(3) { lines ->
    val gammaRate = getGammaRate(lines.toList())
    val epsilonRate = getEpsilonRate(gammaRate)
    gammaRate.toInt(2) * epsilonRate
}

private fun getMostCommonBit(inputs: List<String>, currentIndex: Int) = when {
    inputs.count { it[currentIndex] == '1' } >= inputs.count { it[currentIndex] == '0' } -> '1'
    else -> '0'
}

private fun getGammaRate(lines: List<String>) =
    (0 until lines.first().length).map { getMostCommonBit(lines, it) }.joinToString(separator = "")

private fun getEpsilonRate(gammaRate: String) = gammaRate.toInt(2) xor "1".repeat(gammaRate.length).toInt(2)

fun resolvePartTwo() = readInputLines(3) {
    val lines = it.toList()
    val oxygenGeneratorRate = searchValueWith(lines, ::getMostCommonBit)
    val co2ScrubberRate = searchValueWith(lines, ::getLessCommonBit)

    oxygenGeneratorRate.toInt(2) * co2ScrubberRate.toInt(2)
}

private fun getLessCommonBit(inputs: List<String>, currentIndex: Int) = when {
    inputs.count { it[currentIndex] == '1' } < inputs.count { it[currentIndex] == '0' } -> '1'
    else -> '0'
}

private fun searchValueWith(
    inputs: List<String>,
    commonBitFinder: (inputs: List<String>, currentIndex: Int) -> Char,
    currentIndex: Int = 0
): String {
    val mostCommonBit = commonBitFinder(inputs, currentIndex)
    return inputs.filter { it[currentIndex] == mostCommonBit }.let {
        if (it.size == 1) it.first()
        else searchValueWith(it, commonBitFinder, currentIndex + 1)
    }
}
