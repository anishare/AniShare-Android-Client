package com.anishare.anishare.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anishare.anishare.domain.model.Anime

@Composable
fun RecommendItem(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var to by remember { mutableStateOf("") }
    var anime: Anime? by remember { mutableStateOf(null) }
}