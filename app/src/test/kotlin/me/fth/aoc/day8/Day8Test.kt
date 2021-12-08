package me.fth.aoc.day8

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun `resolvePartOne should found 383 1, 4, 7, and 8`() {
        assertEquals(383, resolvePartOne())
    }
    @Test
    fun `resolvePartTwo should translate all numbers to a sum of 998900`() {
        assertEquals(998900, resolvePartTwo())
    }
}
