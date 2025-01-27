import agh.ics.oop.model.MapDirection
import agh.ics.oop.model.Vector2d
import java.util.*

// Extension function to get a random position from a map
fun Map<Vector2d, *>.randomPosition(): Vector2d? {
    return this.keys.randomOrNull()
}

// Extension function to get a random free position within the map's size
fun Map<Vector2d, *>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val occupiedPositions = this.keys
    val allPositions = (0 until mapSize.x).flatMap { x ->
        (0 until mapSize.y).map { y -> Vector2d(x, y) }
    }
    val freePositions = allPositions.filter { it !in occupiedPositions }
    return freePositions.randomOrNull()
}