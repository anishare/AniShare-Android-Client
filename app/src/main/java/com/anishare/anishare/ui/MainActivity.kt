package com.anishare.anishare.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anishare.anishare.ui.auth.AuthForm
import com.anishare.anishare.ui.theme.AniShareTheme
import com.anishare.anishare.ui.util.AppNavHost
import com.anishare.anishare.util.AniShareScreen
import com.anishare.anishare.util.AuthFormType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniShareTheme {
                AuthForm(AuthFormType.Login)
            }
        }
    }
}

@Composable
fun MainApp() {
    val allScreens = AniShareScreen.values().toList()
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = AniShareScreen.fromRoute(
        backstackEntry.value?.destination?.route
    )
    Scaffold { innerPadding ->
        AppNavHost(navController, modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AniShareTheme {
        AuthForm(AuthFormType.Login)
    }
}