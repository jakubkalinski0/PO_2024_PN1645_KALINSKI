package agh.ics.oop.model

class Animal(
    private var orientation: MapDirection = MapDirection.NORTH,
    override var position: Vector2d = Vector2d(2, 2)
) : WorldElement {

    fun getOrientation(): MapDirection = orientation

    fun getPosition(): Vector2d = position

    override fun toString(): String = orientation.toString()

    fun isAt(position: Vector2d): Boolean = this.position == position

    fun move(direction: MoveDirection, map: WorldMap) {
        when (direction) {
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.FORWARD -> {
                val newPosition = position + orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
            MoveDirection.BACKWARD -> {
                val newPosition = position - orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
        }
    }
}