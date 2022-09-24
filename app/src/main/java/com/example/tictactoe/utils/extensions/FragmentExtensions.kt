package com.example.tictactoe.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun Fragment.async(
    context: CoroutineContext = EmptyCoroutineContext,
    func: suspend (CoroutineScope) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(context) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            func(this@launch)
        }
    }
}