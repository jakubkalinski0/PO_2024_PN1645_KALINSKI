import agh.ics.oop.model.Animal
import agh.ics.oop.model.Vector2d
import agh.ics.oop.model.WorldMap

class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val animals = mutableMapOf<Vector2d, Animal>()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(width - 1, height - 1))
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }

    override fun place(animal: Animal): Boolean {
        var currentPosition = animal.position
        if (!canMoveTo(currentPosition)) {
            throw IllegalArgumentException("Position $currentPosition is out of bounds")
        }

        // If position is occupied, try to find a new position
        if (animals[currentPosition] != null) {
            // Try to find a random free position
            val newPosition = animals.randomFreePosition(Vector2d(width, height))
            if (newPosition != null) {
                // Update the animal's position through reflection since it's private
                val positionField = Animal::class.java.getDeclaredField("_position")
                positionField.isAccessible = true
                positionField.set(animal, newPosition)
                currentPosition = newPosition
            } else {
                // If no free position is available, replace a random existing animal
                val randomPosition = animals.randomPosition()
                if (randomPosition != null) {
                    // Remove the existing animal and update the new animal's position
                    animals.remove(randomPosition)
                    val positionField = Animal::class.java.getDeclaredField("_position")
                    positionField.isAccessible = true
                    positionField.set(animal, randomPosition)
                    currentPosition = randomPosition
                } else {
                    return false
                }
            }
        }

        animals[currentPosition] = animal
        return true
    }
}
