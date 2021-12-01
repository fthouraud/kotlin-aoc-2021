package me.fth.aoc

fun <T> readInputLines(dayNumber: Int, block: (Sequence<String>) -> T): T =
    object {}.javaClass.getResourceAsStream("""/day$dayNumber.txt""")!!.bufferedReader().useLines(block)
