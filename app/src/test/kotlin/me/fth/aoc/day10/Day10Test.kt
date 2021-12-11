package me.fth.aoc.day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun `resolvePartOne should get a score of 387363`() {
        assertEquals(387363, resolvePartOne())
    }
    @Test
    fun `resolvePartTwo should get a score of 4330777059`() {
        assertEquals(4330777059.toBigInteger(), resolvePartTwo())
    }
}
