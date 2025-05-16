package com.chetan.mvicomposecleanarch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chetan.mvicomposecleanarch.data.model.Result
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun MviComposeAppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.Route.CHARACTER_LIST
    ){
        composable(Navigation.Route.CHARACTER_LIST){
            CharacterListDestinations(navController)
        }
        composable("${Navigation.Route.CHARACTER_DETAIL}/{${Navigation.Args.CHARACTER}}"){
            backStackEntry ->
            CharacterDetailDestinations(navController)
        }
    }
}
object Navigation{
    object Args{
        const val CHARACTER = "characterJson"
    }
    object Route{
        const val CHARACTER_LIST = "characterList"
        const val CHARACTER_DETAIL = "characterDetail"
    }
}