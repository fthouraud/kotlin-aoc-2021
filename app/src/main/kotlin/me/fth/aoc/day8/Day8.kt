package me.fth.aoc.day8

import me.fth.aoc.readInputLines

typealias Dictionary = Map<String, Int>

fun resolvePartOne() = readInputLines(8) {
    val uniqueNumberCodeLength = arrayOf(2, 3, 4, 7)
    it.flatMap { line -> line.split(" | ").last().split(" ") }
        .count { code -> code.length in uniqueNumberCodeLength }
}

fun resolvePartTwo() = readInputLines(8) { lines ->
    lines.flatMap { it.split(" | ").map { codes -> codes.split(" ") } }
        .windowed(2, 2)
        .map { getDictionary(it.first()) to it.last() }
        .sumOf { (dictionary, output) ->
            output.map { code ->
                dictionary.keys.find { knownCode ->
                    knownCode.length == code.length && knownCode.toList().containsAll(code.toList())
                }?.let { knownCode -> dictionary[knownCode] }
            }.joinToString("").toInt()
        }
}

fun getDictionary(codes: List<String>): Dictionary {
    val dictionary = mutableMapOf<String, Int>()

    codes.find { it.length == 2 }?.let { one -> dictionary[one] = 1 }
    codes.find { it.length == 4 }?.let { four ->
        dictionary[four] = 4
        codes.find { it.toList().containsAll(four.toList()) && it.length == 6 }?.let { nine -> dictionary[nine] = 9 }
    }
    codes.find { it.length == 3 }?.let { seven ->
        dictionary[seven] = 7
        codes.find { it.toList().containsAll(seven.toList()) && it.length == 6 && !dictionary.containsKey(it) }
            ?.let { zero -> dictionary[zero] = 0 }
        codes.find { it.toList().containsAll(seven.toList()) && it.length == 5 }?.let { three -> dictionary[three] = 3 }
    }
    codes.find { it.length == 7 }?.let { eight -> dictionary[eight] = 8 }
    codes.find { it.length == 6 && !dictionary.containsKey(it) }?.let { six ->
        dictionary[six] = 6
        codes.find { it.toList().intersect(six.toSet()).size == 5 && !dictionary.containsKey(it) }
            ?.let { five -> dictionary[five] = 5 }
    }
    codes.find { !dictionary.containsKey(it) }?.let { two -> dictionary[two] = 2 }

    return dictionary
}
