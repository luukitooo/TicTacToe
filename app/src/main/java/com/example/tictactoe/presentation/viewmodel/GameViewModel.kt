package com.example.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.model.Tile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    private val _tilesFlow = MutableStateFlow(emptyList<Tile>())
    val tilesFlow get() = _tilesFlow.asStateFlow()

    suspend fun generateTiles(count: Int) {
        val list = List(count) {
            Tile(id = it)
        }
        _tilesFlow.emit(list)
    }

    suspend fun tileClicked(tile: Tile, player: String) {
        val finalList = mutableListOf<Tile>()
        finalList.addAll(_tilesFlow.value)
        finalList[tile.id] = Tile(tile.id, player)
        _tilesFlow.emit(finalList)
    }

}