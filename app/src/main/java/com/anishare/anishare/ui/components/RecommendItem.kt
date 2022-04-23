package com.anishare.anishare.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.items
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.util.AniShareScreen

@Composable
fun RecommendItem(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var to by remember { mutableStateOf("") }
    var anime: Anime? by remember { mutableStateOf(null) }

    val context = LocalContext.current

    //TODO: Autocomplete Input field for Anime
    Scaffold(
        topBar = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
            }
        },
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = to,
                singleLine = true,
                onValueChange = { to = it },
                label = {
                    Text(text = "To")
                }
            )
            Divider()
            Button(
                onClick = {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(AniShareScreen.Dashboard.name) {
                        popUpTo(AniShareScreen.Dashboard.name)
                    }
                },
                enabled = to.isNotEmpty() && anime != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Recommend")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecommendItem() {
    RecommendItem(navController = rememberNavController())
}