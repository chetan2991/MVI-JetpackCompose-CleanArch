package com.chetan.mvicomposecleanarch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chetan.mvicomposecleanarch.presentation.features.characterdetails.composables.CharacterDetailScreen

@Composable
fun CharacterDetailDestinations(navController: NavHostController) {
    CharacterDetailScreen(){
        navController.popBackStack()
    }
}
