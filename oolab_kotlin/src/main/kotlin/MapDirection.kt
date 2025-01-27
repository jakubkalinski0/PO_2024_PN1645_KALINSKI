package agh.ics.oop.model

enum class MapDirection {
    NORTH, EAST, SOUTH, WEST;

    override fun toString(): String {
        return when (this) {
            NORTH -> "N"
            EAST -> "E"
            SOUTH -> "S"
            WEST -> "W"
        }
    }

    fun next(): MapDirection {
        return when (this) {
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            NORTH -> EAST
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            EAST -> NORTH
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
        }
    }

    // toUnitVector method
    fun toUnitVector(): Vector2d {
        return when (this) {
            NORTH -> Vector2d(0, 1)
            EAST -> Vector2d(1, 0)
            SOUTH -> Vector2d(0, -1)
            WEST -> Vector2d(-1, 0)
        }
    }
}