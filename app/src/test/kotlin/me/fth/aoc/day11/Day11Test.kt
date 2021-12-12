package me.fth.aoc.day11

import me.fth.aoc.Point
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun `resolvePartOne should count 1729 flashes`() {
        assertEquals(1729, resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should get step 237`() {
        assertEquals(237, resolvePartTwo())
    }

    class MutableMatrixTest {
        private val matrix: OctopusPowerMatrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        )

        @Test
        fun `getAdjacentPositions should give 8 adjacent values`() {
            val position = Point(1, 1)

            val adjacentPositions = matrix.getAdjacentPositions(position)

            assertContains(adjacentPositions, Point(0, 0))
            assertContains(adjacentPositions, Point(0, 1))
            assertContains(adjacentPositions, Point(0, 2))
            assertContains(adjacentPositions, Point(1, 0))
            assertContains(adjacentPositions, Point(2, 0))
            assertContains(adjacentPositions, Point(2, 1))
            assertContains(adjacentPositions, Point(2, 2))
            assertContains(adjacentPositions, Point(1, 2))

            assertEquals(8, adjacentPositions.size)
        }

        @Test
        fun `getAdjacentPositions should ignore top positions outside range`() {
            val position = Point(1, 0)

            val adjacentPositions = matrix.getAdjacentPositions(position)

            assertContains(adjacentPositions, Point(0, 0))
            assertContains(adjacentPositions, Point(2, 0))
            assertContains(adjacentPositions, Point(0, 1))
            assertContains(adjacentPositions, Point(1, 1))
            assertContains(adjacentPositions, Point(2, 1))

            assertEquals(5, adjacentPositions.size)
        }

        @Test
        fun `getAdjacentPositions should ignore left positions outside range`() {
            val position = Point(0, 1)

            val adjacentPositions = matrix.getAdjacentPositions(position)

            assertContains(adjacentPositions, Point(0, 0))
            assertContains(adjacentPositions, Point(0, 2))
            assertContains(adjacentPositions, Point(1, 0))
            assertContains(adjacentPositions, Point(1, 1))
            assertContains(adjacentPositions, Point(1, 2))

            assertEquals(5, adjacentPositions.size)
        }

        @Test
        fun `getAdjacentPositions should ignore right positions outside range`() {
            val position = Point(2, 1)

            val adjacentPositions = matrix.getAdjacentPositions(position)

            assertContains(adjacentPositions, Point(2, 0))
            assertContains(adjacentPositions, Point(2, 2))
            assertContains(adjacentPositions, Point(1, 0))
            assertContains(adjacentPositions, Point(1, 1))
            assertContains(adjacentPositions, Point(1, 2))

            assertEquals(5, adjacentPositions.size)
        }

        @Test
        fun `getAdjacentPositions should ignore bottom positions outside range`() {
            val position = Point(1, 2)

            val adjacentPositions = matrix.getAdjacentPositions(position)

            assertContains(adjacentPositions, Point(2, 2))
            assertContains(adjacentPositions, Point(0, 2))
            assertContains(adjacentPositions, Point(0, 1))
            assertContains(adjacentPositions, Point(1, 1))
            assertContains(adjacentPositions, Point(2, 1))

            assertEquals(5, adjacentPositions.size)
        }
    }
}
