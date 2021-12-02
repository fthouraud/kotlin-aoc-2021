package me.fth.aoc.day2

object AimedLocation: UpdatableLocation {
    private var horizontalPosition = 0
    private var depth = 0
    private var aim = 0

    override val value
        get() = horizontalPosition * depth

    override fun dive(depth: Int) {
        this.aim += depth
    }

    override fun rise(depth: Int) {
        this.aim -= depth
    }

    override fun progress(distance: Int) {
        this.horizontalPosition += distance
        this.depth += aim * distance
    }
}