package me.fth.aoc.day6

import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun `resolvePartOne should count 362666 fish after 80 days`() {
        assertEquals(BigInteger.valueOf(362666), resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should count 1640526601595 fish after 256 days`() {
        assertEquals(BigInteger.valueOf(1640526601595), resolvePartTwo())
    }
}
