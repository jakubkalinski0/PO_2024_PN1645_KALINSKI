package agh.ics.oop.model

class Animal(
    private var orientation: MapDirection = MapDirection.NORTH,
    private var _position: Vector2d = Vector2d(2, 2)
) : WorldElement {
    // Expose position through the interface property
    override val position: Vector2d
        get() = _position

    fun getOrientation(): MapDirection = orientation

    override fun toString(): String = orientation.toString()

    fun isAt(position: Vector2d): Boolean = this._position == position

    fun move(direction: MoveDirection, map: WorldMap) {
        when (direction) {
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.FORWARD -> {
                val newPosition = _position + orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    _position = newPosition
                }
            }
            MoveDirection.BACKWARD -> {
                val newPosition = _position - orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    _position = newPosition
                }
            }
        }
    }
}