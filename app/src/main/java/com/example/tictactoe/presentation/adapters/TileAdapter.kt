package com.example.tictactoe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.databinding.ItemTileBinding
import com.example.tictactoe.model.Tile
import com.example.tictactoe.utils.helper.PlayerUtil

class TileAdapter : ListAdapter<Tile, TileAdapter.TileViewHolder>(TileItemCallback) {

    var onItemClickListener: ((Tile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TileViewHolder(
        ItemTileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bind()
    }

    inner class TileViewHolder(private val binding: ItemTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val tile = getItem(adapterPosition)
            if (tile.value.isNotEmpty())
                binding.btnTile.isEnabled = false
            binding.btnTile.apply {
                text = when (tile.value) {
                    PlayerUtil.X -> PlayerUtil.X
                    PlayerUtil.O -> PlayerUtil.O
                    else -> ""
                }
                setOnClickListener {
                    onItemClickListener?.invoke(tile)
                }
            }
        }
    }

    private object TileItemCallback : DiffUtil.ItemCallback<Tile>() {
        override fun areItemsTheSame(oldItem: Tile, newItem: Tile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tile, newItem: Tile): Boolean {
            return oldItem == newItem
        }
    }
}