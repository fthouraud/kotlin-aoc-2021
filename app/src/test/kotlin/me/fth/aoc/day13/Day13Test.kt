package me.fth.aoc.day13

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun `resolvePartOne should count 678 #`() {
        assertEquals(678, resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should reveal the code ECFHLHZF`() {
        // @formatter:off
        val expectedMatrix = arrayOf(
            charArrayOf('#', '#', '#', '#', '.', '.', '#', '#', '.', '.', '#', '#', '#', '#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.'),
            charArrayOf('#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '#', '.', '#', '.', '.', '.', '.'),
            charArrayOf('#', '#', '#', '.', '.', '#', '.', '.', '.', '.', '#', '#', '#', '.', '.', '#', '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '#', '#', '#', '.', '.', '.', '#', '.', '.', '#', '#', '#', '.', '.'),
            charArrayOf('#', '.', '.', '.', '.', '#', '.', '.', '.', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '.'),
            charArrayOf('#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '.', '#', '.', '.', '.', '.'),
            charArrayOf('#', '#', '#', '#', '.', '.', '#', '#', '.', '.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.', '#', '#', '#', '#', '.', '#', '.', '.', '#', '.', '#', '#', '#', '#', '.', '#', '.', '.', '.', '.')
        )
        // @formatter:on

        val matrix = resolvePartTwo()

        assertEquals(6, matrix.size)

        assertContentEquals(expectedMatrix[0], matrix[0])
        assertContentEquals(expectedMatrix[1], matrix[1])
        assertContentEquals(expectedMatrix[2], matrix[2])
        assertContentEquals(expectedMatrix[3], matrix[3])
        assertContentEquals(expectedMatrix[4], matrix[4])
        assertContentEquals(expectedMatrix[5], matrix[5])
    }
}
