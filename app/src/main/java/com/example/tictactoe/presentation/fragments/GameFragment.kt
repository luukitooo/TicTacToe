package com.example.tictactoe.presentation.fragments

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tictactoe.databinding.FragmentGameBinding
import com.example.tictactoe.model.Tile
import com.example.tictactoe.presentation.adapters.TileAdapter
import com.example.tictactoe.presentation.viewmodel.GameViewModel
import com.example.tictactoe.utils.helper.PlayerUtil
import com.example.tictactoe.utils.base.BaseFragment
import com.example.tictactoe.utils.extensions.async
import com.example.tictactoe.utils.helper.TicTacToeUtil
import com.google.android.material.snackbar.Snackbar

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {

    private var isX = true

    private val args by navArgs<GameFragmentArgs>()

    private val viewModel by viewModels<GameViewModel>()

    private val tileAdapter = TileAdapter()

    override fun viewCreated() {

        init()

        onClickListeners()

        observers()

    }

    private fun init() {
        binding.rvTiles.apply {
            adapter = tileAdapter
            layoutManager = GridLayoutManager(requireContext(), args.spanCount)
        }
        async { viewModel.generateTiles(args.spanCount * args.spanCount) }
    }

    private fun onClickListeners() {
        tileAdapter.onItemClickListener = { tile ->
            if (isX) {
                async { viewModel.tileClicked(tile, PlayerUtil.X) }
            } else {
                async { viewModel.tileClicked(tile, PlayerUtil.O) }
            }
            isX = !isX
        }
    }

    private fun observers() {
        async {
            viewModel.tilesFlow.collect {
                handleListUpdate(it)
            }
        }
    }

    private fun handleListUpdate(tiles: List<Tile>) {
        tileAdapter.submitList(tiles)
        val checkResult = TicTacToeUtil.gameFinished(tiles)
        checkResult?.let {
            findNavController().popBackStack()
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

}