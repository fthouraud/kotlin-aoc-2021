package me.fth.aoc.day6

import me.fth.aoc.readInputLines
import java.math.BigInteger

const val daysBeforeBirth = 6
const val daysBeforeFirstBirth = 8
const val lifecycleStepCount = 9

fun resolvePartOne() = readInputLines(6) {
    countFishAfterDays(it, 80)
}

private fun countFishAfterDays(initialPopulation: Sequence<String>, days: Int): BigInteger {
    val fishCount = Array(lifecycleStepCount) { BigInteger.ZERO }

    with(initialPopulation.first().split(",").map(String::toInt)) {
        (0..8).forEach { i -> fishCount[i] = count { day -> day == i }.toBigInteger() }
    }

    repeat(days) {
        val fishCountOfDay = Array(lifecycleStepCount) { BigInteger.ZERO }
        (8 downTo 0).forEach { fishAge ->
            val count = fishCount[fishAge]
            if (fishAge == 0) {
                fishCountOfDay[daysBeforeBirth] += count
                fishCountOfDay[daysBeforeFirstBirth] = count
            } else fishCountOfDay[fishAge - 1] += count
        }
        fishCountOfDay.forEachIndexed { i, count -> fishCount[i] = count }
    }

    return fishCount.reduce { sum, count -> (sum ?: BigInteger.ZERO) + count }
}

fun resolvePartTwo() = readInputLines(6) {
    countFishAfterDays(it, 256)
}
