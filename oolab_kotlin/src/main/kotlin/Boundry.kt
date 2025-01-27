package agh.ics.oop.model.util

import agh.ics.oop.model.Vector2d

@JvmRecord
data class Boundary(val lowerLeft: Vector2d, val upperRight: Vector2d)