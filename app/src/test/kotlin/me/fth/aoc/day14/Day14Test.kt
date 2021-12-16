package me.fth.aoc.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun `resolvePartOne should get a polymer strength of 3095 after 10 iterations`() {
        assertEquals(3095.toBigInteger(), resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should get a polymer strength of 3095 after 40 iterations`() {
        assertEquals(3095.toBigInteger(), resolvePartTwo())
    }
}
