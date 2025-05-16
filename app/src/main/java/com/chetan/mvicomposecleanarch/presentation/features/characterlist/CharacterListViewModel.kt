package com.chetan.mvicomposecleanarch.presentation.features.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.mvicomposecleanarch.base.common.ApiResponse
import com.chetan.mvicomposecleanarch.data.model.Result
import com.chetan.mvicomposecleanarch.domain.GetCharacterListUseCase
import com.chetan.mvicomposecleanarch.presentation.common.UViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel(),
    UViewModel<CharacterListContract.CharacterListPageState, CharacterListContract.Event, CharacterListContract.Effect> {
    private val _state = MutableStateFlow(CharacterListContract.CharacterListPageState())
    override val state: StateFlow<CharacterListContract.CharacterListPageState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<CharacterListContract.Effect>()
    override val effect: SharedFlow<CharacterListContract.Effect> = _effect.asSharedFlow()
    override fun event(event: CharacterListContract.Event) {
       when(event){
           is CharacterListContract.Event.FetchCharacters -> {
               fetchCharacters()
           }
           is CharacterListContract.Event.CharacterClicked -> {
               viewModelScope.launch {
                   _effect.emit(CharacterListContract.Effect.NavigateToCharacterDetails(event.character))
               }
           }
       }
    }

    private fun fetchCharacters(){
        viewModelScope.launch{
            getCharacterListUseCase()
                .onStart{
                    _state.update { it.copy(pageState = CharacterListContract.PageState.Loading) }
                }.onEach {  result ->
                    when(result){
                        is ApiResponse.Loading -> {
                            _state.update { it.copy(pageState = CharacterListContract.PageState.Loading) }
                        }

                        is ApiResponse.Error -> {
                            _state.update { it.copy(pageState = CharacterListContract.PageState.Error(result.message?:"Unknown Error")) }
                        }
                        is ApiResponse.Success -> {
                            _state.update { it.copy(pageState = CharacterListContract.PageState.Success(result.data?: emptyList())) }
                        }
                    }
                }.catch { error ->
                    _state.update { it.copy(pageState = CharacterListContract.PageState.Error(error.message?:"Unknown Error")) }
                }
                .collect{

            }

        }
    }
}