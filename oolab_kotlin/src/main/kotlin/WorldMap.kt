package agh.ics.oop.model

import agh.ics.oop.model.util.Boundary
import java.util.*

interface WorldMap {
    fun place(animal: Animal): Boolean
    fun move(animal: Animal, direction: MoveDirection)
    fun isOccupied(position: Vector2d): Boolean
    fun objectAt(position: Vector2d): WorldElement?
    fun getElements(): Collection<WorldElement>
    fun getCurrentBounds(): Boundary
    fun getId(): UUID
    fun canMoveTo(newPosition: Vector2d): Boolean
}