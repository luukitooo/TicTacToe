package com.example.tictactoe.presentation.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoe.databinding.FragmentMenuBinding
import com.example.tictactoe.presentation.adapters.DifficultyAdapter
import com.example.tictactoe.utils.base.BaseFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    private val difficultyAdapter = DifficultyAdapter(
        List(7) {
            it
        }.filter { it > 2 }
    )

    override fun viewCreated() {

        init()

        onClickListeners()

    }

    private fun init() {
        binding.rvDifficulties.apply {
            adapter = difficultyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onClickListeners() {
        difficultyAdapter.onItemClickListener = {
            navigateToGame(it)
        }
    }

    private fun navigateToGame(spanCount: Int) {
        findNavController().navigate(
            MenuFragmentDirections.toGameFragment(
                spanCount = spanCount
            )
        )
    }

}