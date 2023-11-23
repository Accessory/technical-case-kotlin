package de.hermes.technicalcasekotlin.models

import kotlin.math.max
import kotlin.math.min

data class Line(val start: Position, val end: Position) {
    fun intersections(other: Line): Int {
        val selfIsHorizontal = this.isHorizontal()
        val otherIsHorizontal = other.isHorizontal()

        return when {
            selfIsHorizontal && otherIsHorizontal -> if (start.y == other.start.y) other.getHorizontalOverlap(this) else 0
            !selfIsHorizontal && !otherIsHorizontal -> if (start.x == other.start.x) getVerticalOverlap(other) else 0
            selfIsHorizontal && !otherIsHorizontal -> if (hasIntersection(other)) 1 else 0
            else -> if (other.hasIntersection(this)) 1 else 0
        }
    }

    private fun hasIntersection(other: Line): Boolean {
        val thisStartX = min(start.x, end.x)
        val thisEndX = max(start.x, end.x)
        if (thisStartX > other.start.x || thisEndX < other.start.x) {
            return false
        }

        val otherStartY = min(other.start.y, other.end.y)
        val otherEndY = max(other.start.y, other.end.y)

        return start.y in (otherStartY + 1) until otherEndY
    }

    private fun getVerticalOverlap(other: Line): Int {
        val start = min(this.start.y, this.end.y)
        val end = max(this.start.y, this.end.y)
        val otherStart = min(other.start.y, other.end.y)
        val otherEnd = max(other.start.y, other.end.y)

        return overlapping(start, end, otherStart, otherEnd)
    }

    private fun getHorizontalOverlap(other: Line): Int {
        val start = min(this.start.x, this.end.x)
        val end = max(this.start.x, this.end.x)
        val otherStart = min(other.start.x, other.end.x)
        val otherEnd = max(other.start.x, other.end.x)

        return overlapping(start, end, otherStart, otherEnd)
    }

    private fun overlapping(start: Int, end: Int, otherStart: Int, otherEnd: Int): Int {
        val startMax = max(otherStart, start)
        val endMin = min(otherEnd, end)
        val overlap = endMin - startMax

        return if (overlap == 0) 1 else max(0, overlap + 1)
    }

    private fun isHorizontal(): Boolean {
        return start.y == end.y
    }
}
