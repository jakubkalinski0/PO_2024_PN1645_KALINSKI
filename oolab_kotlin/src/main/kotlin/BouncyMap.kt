import agh.ics.oop.model.Animal
import agh.ics.oop.model.Vector2d
import agh.ics.oop.model.WorldMap

abstract class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val animals = mutableMapOf<Vector2d, Animal>()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(width - 1, height - 1))
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }

    override fun place(animal: Animal): Boolean {
        val position = animal.getPosition()
        if (!canMoveTo(position)) {
            throw IllegalArgumentException("Position $position is out of bounds")
        }

        if (animals[position] != null) {
            // Handle bounce behavior
            val newFreePosition = animals.randomFreePosition(Vector2d(width, height))
            if (newFreePosition != null) {
                animals[newFreePosition] = animal
                return true
            } else {
                // Replace a random animal
                val randomPosition = animals.randomPosition()
                if (randomPosition != null) {
                    animals[randomPosition] = animal
                    return true
                }
                return false // No free position and no random position to replace
            }
        } else {
            animals[position] = animal
            return true
        }
    }

}
