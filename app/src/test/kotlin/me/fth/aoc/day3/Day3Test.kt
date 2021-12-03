package me.fth.aoc.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun `resolvePartOne should return 1307354 as power consumption`() {
        assertEquals(1307354, resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should return 482500 as life support rating`() {
        assertEquals(482500, resolvePartTwo())
    }
}
