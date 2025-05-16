package com.chetan.mvicomposecleanarch.presentation.features.characterlist.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chetan.mvicomposecleanarch.data.model.Result
import com.chetan.mvicomposecleanarch.presentation.common.collectInLaunchedEffect
import com.chetan.mvicomposecleanarch.presentation.common.composables.ErrorScreen
import com.chetan.mvicomposecleanarch.presentation.common.composables.ProgressIndicatorScreen
import com.chetan.mvicomposecleanarch.presentation.common.use
import com.chetan.mvicomposecleanarch.presentation.features.characterlist.CharacterListContract
import com.chetan.mvicomposecleanarch.presentation.features.characterlist.CharacterListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(characherListViewModel: CharacterListViewModel = hiltViewModel(), modifier: Modifier = Modifier, onCharacterItemClick: (character: Result) -> Unit = {}) {
    val(state, event, effect ) = use(characherListViewModel)
   effect.collectInLaunchedEffect { effect ->
       when(effect){
           CharacterListContract.Effect.CharacteFetchSuccess -> {}
           is CharacterListContract.Effect.ShowToast -> event(CharacterListContract.Event.FetchCharacters)
           is CharacterListContract.Effect.NavigateToCharacterDetails -> {onCharacterItemClick(effect.character)}
       }
   }
    LaunchedEffect(Unit) {
        event(CharacterListContract.Event.FetchCharacters)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Character List"
                    ,color = MaterialTheme.colorScheme.onPrimary)
                },
                navigationIcon = {
                    // Add navigation icon if needed
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { 
        innerPadding ->
        when(state.pageState){
            is CharacterListContract.PageState.Error -> {
               ErrorScreen(errorMessage = state.pageState.message)
            }
            CharacterListContract.PageState.Loading -> {
                ProgressIndicatorScreen()
            }
            is CharacterListContract.PageState.Success -> {
                LazyColumn(contentPadding = innerPadding) {
                    items(state.pageState.characterList){ character ->
                        CharacterItemCard(character = character, { event(CharacterListContract.Event.CharacterClicked(character)) })
                    }
                }
            }
        }

    }
    
}