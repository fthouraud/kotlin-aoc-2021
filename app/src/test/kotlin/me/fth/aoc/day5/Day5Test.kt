package me.fth.aoc.day5

import me.fth.aoc.Line
import me.fth.aoc.Point
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class Day5Test {
    @Test
    fun `resolvePartOne should return 8060 as power consumption`() {
        assertEquals(8060, resolvePartOne())
    }

    @Test
    fun `resolvePartTwo should return 21810 as power consumption`() {
        assertEquals(21810, resolvePartTwo())
    }
    @Test
    fun `getPoints should return 3 points`() {
        val linePoints = Line(Point(1, 1), Point(3, 3)).getPoints()

        assertEquals(3, linePoints.size)
        assertContains(linePoints, Point(1, 1))
        assertContains(linePoints, Point(2, 2))
        assertContains(linePoints, Point(3, 3))
    }
    @Test
    fun `getPoints should return 3 points (2)`() {
        val linePoints = Line(Point(9, 7), Point(7, 9)).getPoints()

        assertEquals(3, linePoints.size)
        assertContains(linePoints, Point(9, 7))
        assertContains(linePoints, Point(8, 8))
        assertContains(linePoints, Point(7, 9))
    }
    @Test
    fun `getPoints should return 5 points`() {
        val linePoints = Line(Point(6, 4), Point(2, 0)).getPoints()

        assertEquals(5, linePoints.size)
        assertContains(linePoints, Point(6, 4))
        assertContains(linePoints, Point(5, 3))
        assertContains(linePoints, Point(4, 2))
        assertContains(linePoints, Point(3, 1))
        assertContains(linePoints, Point(2, 0))
    }
    @Test
    fun `getPoints should return 3 points (4)`() {
        val linePoints = Line(Point(5, 5), Point(8, 2)).getPoints()

//        assertEquals(4, linePoints.size)
        assertContains(linePoints, Point(9, 7))
        assertContains(linePoints, Point(8, 8))
        assertContains(linePoints, Point(7, 9))
    }
}
