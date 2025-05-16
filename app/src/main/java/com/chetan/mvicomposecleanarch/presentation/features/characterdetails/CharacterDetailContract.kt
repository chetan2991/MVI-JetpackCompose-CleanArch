package com.chetan.mvicomposecleanarch.presentation.features.characterdetails

import com.chetan.mvicomposecleanarch.data.model.Result

interface CharacterDetailContract {
    sealed interface PageState {
        object Loading : PageState
        data class Success(val character: Result) : PageState
        data class Error(val message: String) : PageState
    }
    data class CharacterDetailPageState(
        val pageState: PageState = PageState.Loading,
        val character: Result? = null
    )
    sealed interface Event {
         object LoadCharacterDetails : Event
        object BackClicked : Event
    }
    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
        object BackClicked : Effect
    }
}