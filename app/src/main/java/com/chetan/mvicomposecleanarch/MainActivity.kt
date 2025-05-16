package com.chetan.mvicomposecleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chetan.mvicomposecleanarch.presentation.navigation.MviComposeAppNavigation
import com.chetan.mvicomposecleanarch.ui.theme.MvicomposecleanarchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvicomposecleanarchTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 5.dp,
                    shadowElevation = 10.dp,
                ) {
                    MviComposeAppNavigation()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvicomposecleanarchTheme {
        MviComposeAppNavigation()
    }
}
