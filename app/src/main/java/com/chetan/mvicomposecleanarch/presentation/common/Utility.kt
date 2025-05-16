package com.chetan.mvicomposecleanarch.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
inline fun <reified STATE, EVENT, EFFECT> use(viewmodel: UViewModel<STATE, EVENT, EFFECT>): StateEventEffect<STATE, EVENT, EFFECT> {
    val state by viewmodel.state.collectAsStateWithLifecycle()
    return StateEventEffect(state, { viewmodel.event(it) }, viewmodel.effect)
}

interface UViewModel<STATE, EVENT, EFFECT> {
    val state: StateFlow<STATE>
    val effect: SharedFlow<EFFECT>
    fun event(event: EVENT)
}

data class StateEventEffect<STATE, EVENT, EFFECT>(
    val state: STATE,
    val event: (EVENT) -> Unit,
    val effect: SharedFlow<EFFECT>
)

@Composable
fun <T> SharedFlow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val sharedFlow = this
    LaunchedEffect(key1 = sharedFlow) {
        sharedFlow.collectLatest(function)
    }
}