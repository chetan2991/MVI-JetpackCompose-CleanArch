package com.chetan.mvicomposecleanarch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.chetan.mvicomposecleanarch.presentation.features.characterlist.composables.CharacterListScreen
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun CharacterListDestinations(navController: NavHostController){
        CharacterListScreen(modifier = Modifier){
            val characterJson = Gson().toJson(it)
            val json = URLEncoder.encode(characterJson, "UTF-8")

            navController.navigate("${Navigation.Route.CHARACTER_DETAIL}/$json")
        }
}