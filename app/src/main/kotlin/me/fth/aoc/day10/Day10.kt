package me.fth.aoc.day10

import me.fth.aoc.readInputLines
import java.math.BigInteger
import java.util.ArrayDeque

val closingCharByStartingChar = mapOf(
    ')' to '(',
    '}' to '{',
    ']' to '[',
    '>' to '<',
)

val syntaxScoreByCharacter = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137,
)

fun resolvePartOne() = readInputLines(10) { lines ->
    lines.mapNotNull { line ->
        with(ArrayDeque<Char>()) {
            line.onEach { c ->
                if (isExpectedClosingChar(c)) return@mapNotNull c
                if (c in closingCharByStartingChar.values) push(c) else pop()
            }
        }
        null
    }.sumOf { syntaxScoreByCharacter[it]!! }
}

fun ArrayDeque<Char>.isExpectedClosingChar(c: Char) =
    c in closingCharByStartingChar.keys && peek() != closingCharByStartingChar[c]

val startingCharByClosingChar = closingCharByStartingChar.map { (key, value) -> value to key }.toMap()

val scoreMultiplier = 5.toBigInteger()

val autocompletionScoreByCharacter = mapOf(
    ')' to 1.toBigInteger(),
    ']' to 2.toBigInteger(),
    '}' to 3.toBigInteger(),
    '>' to 4.toBigInteger(),
)

fun resolvePartTwo(): BigInteger = readInputLines(10) { lines ->
    lines.mapNotNull { line ->
        with(ArrayDeque<Char>()) {
            line.forEach { c ->
                if (isExpectedClosingChar(c)) return@mapNotNull null
                if (c in closingCharByStartingChar.values) push(c) else pop()
            }
            map(startingCharByClosingChar::get).fold(BigInteger.ZERO) { total, c -> (total * scoreMultiplier) + autocompletionScoreByCharacter[c]!! }
        }
    }.toList().sorted().let { it[it.size / 2] }
}
