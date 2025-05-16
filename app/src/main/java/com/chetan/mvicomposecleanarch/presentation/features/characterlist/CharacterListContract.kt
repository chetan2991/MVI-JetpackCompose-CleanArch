package com.chetan.mvicomposecleanarch.presentation.features.characterlist

import com.chetan.mvicomposecleanarch.data.model.Result

interface CharacterListContract {
    sealed interface PageState{
        object Loading : PageState
        data class Success(val characterList : List<Result>) : PageState
        data class Error(val message : String) : PageState
    }
    data class CharacterListPageState(
        val pageState : PageState = PageState.Loading,
        val characterList : List<Result> = emptyList()
    )
    sealed interface Event {
        object FetchCharacters : Event
        data class CharacterClicked(val character : Result) : Event
    }
    sealed interface Effect {
        data class ShowToast(val message : String) : Effect
        object CharacteFetchSuccess : Effect
        data class NavigateToCharacterDetails(val character : Result) : Effect
    }
}