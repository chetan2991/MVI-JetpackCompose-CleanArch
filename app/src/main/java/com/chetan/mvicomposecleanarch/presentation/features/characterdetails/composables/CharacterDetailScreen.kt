package com.chetan.mvicomposecleanarch.presentation.features.characterdetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.chetan.mvicomposecleanarch.R
import com.chetan.mvicomposecleanarch.presentation.common.composables.ErrorScreen
import com.chetan.mvicomposecleanarch.presentation.common.collectInLaunchedEffect
import com.chetan.mvicomposecleanarch.presentation.common.composables.ProgressIndicatorScreen
import com.chetan.mvicomposecleanarch.presentation.common.use
import com.chetan.mvicomposecleanarch.presentation.features.characterdetails.CharacterDetailContract
import com.chetan.mvicomposecleanarch.presentation.features.characterdetails.CharacterDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(characterDetailViewModel: CharacterDetailViewModel = hiltViewModel(), onBackClick : () -> Unit){

    val(state, event, effect) = use(characterDetailViewModel)
    effect.collectInLaunchedEffect(){
        when(it){
            CharacterDetailContract.Effect.BackClicked -> onBackClick()
            is CharacterDetailContract.Effect.ShowToast -> {

            }
        }
    }
    LaunchedEffect(Unit){
        event(CharacterDetailContract.Event.LoadCharacterDetails)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.character_details_screen_title),
                    color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick()},
                        colors = IconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,

                        )

                    ){
                        Icon(Icons.AutoMirrored.Default.ArrowBack,
                             contentDescription = "Back")
                    }
                }
            )
        }
    ){ innerPadding ->

        when(state.pageState){
            is CharacterDetailContract.PageState.Error -> {
                ErrorScreen(errorMessage = state.pageState.message)
            }
            CharacterDetailContract.PageState.Loading -> {
                ProgressIndicatorScreen()
            }
            is CharacterDetailContract.PageState.Success -> {
                Column (
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    AsyncImage(
                        modifier = Modifier.size(dimensionResource(R.dimen.character_item_image_size)),
                        model =state.character?.image,
                        contentDescription = "Character Image",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                        ,error = painterResource(R.drawable.ic_launcher_background),
                        placeholder = painterResource(R.drawable.ic_launcher_background)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                    Text(text = "Character Name : ${state.character?.name}")
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                    Text(text = "Character Status : ${state.character?.status}")
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
                    Text(text = "Character Species : ${state.character?.species}")

                }
            }
        }

    }

}
@Preview(showBackground = true)
@Composable
fun CharacterDetailScreenPreview(){
//    //
//    CharacterDetailScreen(character = Result("", emptyList(), "", 0, "", Location("", ""), "Charater1", Origin("", ""), "", "", "", "")){
//
//    }
}