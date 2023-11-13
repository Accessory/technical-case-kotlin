package de.hermes.technicalcasekotlin.enums

enum class Direction {
    North,
    West,
    South,
    East,
}

fun convertToDirection(source: String): Direction? =
        when (source.lowercase()) {
            "north" -> Direction.North
            "south" -> Direction.South
            "east" -> Direction.East
            "west" -> Direction.West
            else -> null
        }

