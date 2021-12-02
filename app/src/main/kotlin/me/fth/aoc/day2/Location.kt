package me.fth.aoc.day2

object Location: UpdatableLocation {
    private var horizontalPosition = 0
    private var depth = 0

    override val value
        get() = horizontalPosition * depth

    override fun dive(depth: Int) {
        this.depth += depth
    }

    override fun rise(depth: Int) {
        this.depth -= depth
    }

    override fun progress(distance: Int) {
        this.horizontalPosition += distance
    }
}