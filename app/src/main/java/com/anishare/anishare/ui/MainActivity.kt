package com.anishare.anishare.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anishare.anishare.model.Anime
import com.anishare.anishare.model.UserResponse
import com.anishare.anishare.ui.components.ElementItem
import com.anishare.anishare.ui.components.UserResponseList
import com.anishare.anishare.ui.data.UserViewModel
import com.anishare.anishare.ui.theme.AniShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniShareTheme {
                // A surface container using the 'background' color from the theme
                UserResponseList()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AniShareTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ElementItem(
                data = UserResponse.mock()
            )
        }
    }
}