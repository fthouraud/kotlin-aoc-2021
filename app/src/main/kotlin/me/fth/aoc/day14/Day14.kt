package me.fth.aoc.day14

import me.fth.aoc.readInputLines
import java.io.File
import java.math.BigInteger
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

fun resolvePartOne() = readInputLines(14) {
    val lines = it.toList()
    val template = lines.first()
    val pairInsertionRules = parsePairInsertionRules(lines.drop(2))

    val polymer = buildOptimizedPolymer(template.toList(), 10, pairInsertionRules)
    getPolymerStrength(polymer)
}

fun parsePairInsertionRules(lines: List<String>) = lines.filter { it.isNotBlank() }
    .map { it.split(" -> ") }.associate { (sequence, value) -> sequence to value.first() }

fun buildPolymerWithFile(template: String, iterations: Int, pairInsertionRules: Map<String, Char>): String {
    var polymer = template.toByteArray()
    val sizeIncrease = AtomicLong(3)
    repeat(iterations) { iter ->
        File.createTempFile("aoc-day14-", "iteration-$iter").let { file ->
            file.printWriter().use {
                it.print(polymer.first().toInt().toChar())
                val nextIncrease = polymer.size + sizeIncrease.get()
                println("#$iter => $nextIncrease")
                (1 until nextIncrease step 2).forEach { i ->
                    if (i >= polymer.size) return@forEach
                    it.print(pairInsertionRules[polymer[i.toInt() - 1].toInt().toChar() + polymer[i.toInt()].toInt().toChar()])
                    it.print(polymer[i.toInt()].toInt().toChar())
                }
                sizeIncrease.updateAndGet { c -> c * 3 }
                it.flush()
            }
            polymer = file.readBytes()
            file.delete()
        }
    }
    return ""
}

fun getPolymerStrength(polymer: List<Char>) =
    polymer.associateWith { a -> polymer.count { b -> a == b }.toBigInteger() }.let { counts ->
        println(counts)
        counts.maxOf { (_, count) -> count } - counts.minOf { (_, count) -> count }
    }

fun resolvePartTwo() = readInputLines(14) {
    val lines = it.toList()
    val template = lines.first()
    val pairInsertionRules = parsePairInsertionRules(lines.drop(2))

    buildPolymerWithFile(template, 40, pairInsertionRules)
//    val polymer = buildOptimizedPolymer(template, 40, pairInsertionRules)

//    getPolymerStrength(polymer)
    BigInteger.ZERO
}

fun buildOptimizedPolymer(basePolymer: List<Char>, iterations: Int, pairInsertionRules: Map<String, Char>): List<Char> {
    val newPolymer = basePolymer.toMutableList()
    val sizeIncrease = AtomicInteger(3)
    repeat(iterations) {
        val nextIncrease = newPolymer.size + sizeIncrease.get()
        println("#$it => $nextIncrease")
        (1 until nextIncrease step 2).forEach { i ->
            if (i >= newPolymer.size) return@forEach
            newPolymer.add(i, pairInsertionRules[newPolymer[i - 1] + newPolymer[i]]!!)
        }
        sizeIncrease.updateAndGet { c -> c * 3 }
    }
    return newPolymer
}

operator fun Char.plus(other: Char) = "$this$other"
