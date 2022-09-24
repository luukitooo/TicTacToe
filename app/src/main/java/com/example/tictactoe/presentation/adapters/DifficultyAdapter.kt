package com.example.tictactoe.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.databinding.ItemDifficultyBinding

class DifficultyAdapter(private val difficulties: List<Int>): RecyclerView.Adapter<DifficultyAdapter.DifficultyViewHolder>() {

    var onItemClickListener: ((Int) -> Unit)? = null

    inner class DifficultyViewHolder(private val binding: ItemDifficultyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val difficult = difficulties[adapterPosition]
            binding.root.apply {
                text = "$difficult x $difficult"
                setOnClickListener {
                    onItemClickListener?.invoke(difficult)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DifficultyViewHolder(
        ItemDifficultyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DifficultyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = difficulties.size
}