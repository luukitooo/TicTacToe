package com.example.tictactoe.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TicTacToe)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}