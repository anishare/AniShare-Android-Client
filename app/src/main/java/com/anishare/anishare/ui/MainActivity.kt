package com.anishare.anishare.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anishare.anishare.ui.login.Login
import com.anishare.anishare.ui.theme.AniShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniShareTheme {
                // A surface container using the 'background' color from the theme
                Login()
            //                UserResponseList()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AniShareTheme {
        Login()
    }
}