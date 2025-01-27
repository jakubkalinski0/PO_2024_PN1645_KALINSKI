import agh.ics.oop.model.Animal
import agh.ics.oop.model.MapDirection
import agh.ics.oop.model.MoveDirection
import agh.ics.oop.model.Vector2d
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : FunSpec({
    context("BouncyMap basic operations") {
        test("should place animal at empty position") {
            val map = BouncyMap(5, 5)
            val animal = Animal(MapDirection.NORTH, Vector2d(2, 2))

            val result = map.place(animal)

            result shouldBe true
            map.objectAt(Vector2d(2, 2)) shouldBe animal
        }

        test("should not place animal outside map bounds") {
            val map = BouncyMap(5, 5)
            val animal = Animal(MapDirection.NORTH, Vector2d(6, 6))

            val exception = runCatching { map.place(animal) }.exceptionOrNull()

            exception shouldNotBe null
            exception should { it is IllegalArgumentException }
        }
    }

    context("BouncyMap collision handling") {
        test("should bounce animal to random position on collision") {
            val map = BouncyMap(3, 3)
            val animal1 = Animal(MapDirection.NORTH, Vector2d(1, 1))
            val animal2 = Animal(MapDirection.SOUTH, Vector2d(1, 1))

            map.place(animal1)
            val result = map.place(animal2)

            result shouldBe true
            map.objectAt(Vector2d(1, 1)) shouldBe animal1
            animal2.position shouldNotBe Vector2d(1, 1)
        }

        test("should handle completely filled map") {
            val map = BouncyMap(2, 2)
            val positions = listOf(
                Vector2d(0, 0),
                Vector2d(0, 1),
                Vector2d(1, 0),
                Vector2d(1, 1)
            )

            // Fill the map
            positions.forEach { pos ->
                map.place(Animal(MapDirection.NORTH, pos))
            }

            // Try to place one more animal
            val newAnimal = Animal(MapDirection.NORTH, Vector2d(0, 0))
            val result = map.place(newAnimal)

            result shouldBe true
            map.objectAt(newAnimal.position) shouldBe newAnimal
        }
    }

    context("BouncyMap movement validation") {
        test("should allow movement within bounds") {
            val map = BouncyMap(5, 5)

            map.canMoveTo(Vector2d(0, 0)) shouldBe true
            map.canMoveTo(Vector2d(4, 4)) shouldBe true
            map.canMoveTo(Vector2d(2, 2)) shouldBe true
        }

        test("should prevent movement outside bounds") {
            val map = BouncyMap(5, 5)

            map.canMoveTo(Vector2d(-1, 0)) shouldBe false
            map.canMoveTo(Vector2d(0, -1)) shouldBe false
            map.canMoveTo(Vector2d(5, 5)) shouldBe false
        }
    }

    context("Animal movement on BouncyMap") {
        test("should move animal correctly") {
            val map = BouncyMap(5, 5)
            val animal = Animal(MapDirection.NORTH, Vector2d(2, 2))
            map.place(animal)

            animal.move(MoveDirection.FORWARD, map)
            animal.position shouldBe Vector2d(2, 3)

            animal.move(MoveDirection.RIGHT, map)
            animal.getOrientation() shouldBe MapDirection.EAST

            animal.move(MoveDirection.BACKWARD, map)
            animal.position shouldBe Vector2d(1, 3)
        }

        test("should prevent movement outside map bounds") {
            val map = BouncyMap(5, 5)
            val animal = Animal(MapDirection.NORTH, Vector2d(2, 4))
            map.place(animal)

            animal.move(MoveDirection.FORWARD, map)
            animal.position shouldBe Vector2d(2, 4) // Position shouldn't change
        }
    }
})