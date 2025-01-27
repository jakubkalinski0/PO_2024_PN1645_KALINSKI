package agh.ics.oop.model

import java.util.*

data class Vector2d(val x: Int, val y: Int) {
    operator fun plus(other: Vector2d): Vector2d = Vector2d(x + other.x, y + other.y)
    operator fun minus(other: Vector2d): Vector2d = Vector2d(x - other.x, y - other.y)

    fun follows(other: Vector2d): Boolean = x >= other.x && y >= other.y
    fun precedes(other: Vector2d): Boolean = x <= other.x && y <= other.y

    fun upperRight(other: Vector2d): Vector2d = Vector2d(maxOf(x, other.x), maxOf(y, other.y))
    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(minOf(x, other.x), minOf(y, other.y))
    fun opposite(): Vector2d = Vector2d(-x, -y)

    override fun toString(): String = "($x, $y)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector2d) return false
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int = Objects.hash(x, y)
}