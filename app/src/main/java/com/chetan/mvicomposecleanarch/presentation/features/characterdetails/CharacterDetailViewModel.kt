package com.chetan.mvicomposecleanarch.presentation.features.characterdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.mvicomposecleanarch.data.model.Result
import com.chetan.mvicomposecleanarch.presentation.common.UViewModel
import com.chetan.mvicomposecleanarch.presentation.navigation.Navigation
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject
@HiltViewModel
class CharacterDetailViewModel@Inject constructor( savedStateHandle: SavedStateHandle):
    ViewModel(),
    UViewModel<CharacterDetailContract.CharacterDetailPageState, CharacterDetailContract.Event, CharacterDetailContract.Effect> {
    private val _state = MutableStateFlow(CharacterDetailContract.CharacterDetailPageState())
    override val state: StateFlow<CharacterDetailContract.CharacterDetailPageState> = _state.asStateFlow()

    init {
        val characterJson = savedStateHandle.get<String>(Navigation.Args.CHARACTER)
        val decodedJson = URLDecoder.decode(characterJson, "UTF-8")
        val character = Gson().fromJson(decodedJson, Result::class.java)
        _state.update { it.copy( pageState = CharacterDetailContract.PageState.Success(character = character), character = character) }
    }

    private val _effect = MutableSharedFlow<CharacterDetailContract.Effect>()
    override val effect: SharedFlow<CharacterDetailContract.Effect> = _effect.asSharedFlow()

    override fun event(event: CharacterDetailContract.Event) {
        when(event){
            is CharacterDetailContract.Event.LoadCharacterDetails -> {
                    _state.update { it.copy(pageState = it.pageState) }
            }
            CharacterDetailContract.Event.BackClicked -> {
                viewModelScope.launch {
                    _effect.emit(CharacterDetailContract.Effect.BackClicked)
                }
            }
        }
    }
}