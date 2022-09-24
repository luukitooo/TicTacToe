package com.example.tictactoe.utils.helper

import com.example.tictactoe.model.Tile
import kotlin.math.sqrt

object TicTacToeUtil {

    fun checkHorizontals(tiles: List<Tile>): String? {
        val matrix = tiles.windowed(
            sqrt(tiles.size.toDouble()).toInt(),
            sqrt(tiles.size.toDouble()).toInt()
        )
        matrix.forEach { list ->
            val values = list.map { it.value }
            if (values.toSet().size == 1 && !values.contains(""))
                return values[0]
        }
        return null
    }

    fun checkVerticals(tiles: List<Tile>): String? {
        val spanCount = sqrt(tiles.size.toDouble()).toInt()
        val matrix = tiles.map { it.value }.windowed(
            spanCount,
            spanCount
        )
        val finalMatrix = MutableList(spanCount) {
            mutableListOf<String>()
        }
        repeat(spanCount) { id ->
            matrix.forEach { list ->
                finalMatrix[id].add(list[id])
            }
        }
        finalMatrix.forEach { list ->
            if (list.toSet().size == 1 && !list.contains(""))
                return list[0]
        }
        return null
    }

    fun checkLeftDiagonal(tiles: List<Tile>): String? {
        val spanCount = sqrt(tiles.size.toDouble()).toInt()
        val leftDiagonal = mutableListOf<String>()
        var position = 0
        repeat(spanCount) {
            leftDiagonal.add(tiles[position].value)
            position += spanCount + 1
        }
        if (leftDiagonal.toSet().size == 1 && !leftDiagonal.contains("")) {
            return leftDiagonal[0]
        }
        return null
    }

    fun checkRightDiagonal(tiles: List<Tile>): String? {
        val spanCount = sqrt(tiles.size.toDouble()).toInt()
        val rightDiagonal = mutableListOf<String>()
        var position = spanCount - 1
        repeat(spanCount) {
            rightDiagonal.add(tiles[position].value)
            position += spanCount - 1
        }
        if (rightDiagonal.toSet().size == 1 && !rightDiagonal.contains("")) {
            return rightDiagonal[0]
        }
        return null
    }

    fun gameFinished(tiles: List<Tile>): String? {
        checkVerticals(tiles)?.let {
            return "$it won..."
        }
        checkHorizontals(tiles)?.let {
            return "$it won..."
        }
        checkLeftDiagonal(tiles)?.let {
            return "$it won..."
        }
        checkRightDiagonal(tiles)?.let {
            return "$it won..."
        }
        if (isDraw(tiles)) {
            return "Draw..."
        }
        return null
    }

    fun isDraw(tiles: List<Tile>): Boolean {
        val checkers = arrayOf(
            checkHorizontals(tiles),
            checkVerticals(tiles),
            checkLeftDiagonal(tiles),
            checkRightDiagonal(tiles)
        )
        if (!tiles.map { it.value }.contains("") && checkers.all { it == null })
            return true
        return false
    }

}