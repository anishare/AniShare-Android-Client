package com.anishare.anishare.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.ui.data.AnimeViewModel
import com.anishare.anishare.ui.data.UserViewModel
import com.anishare.anishare.ui.util.UserDataEvent
import com.anishare.anishare.util.AniShareScreen

@Composable
fun RecommendItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    animeViewModel: AnimeViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val animeList by animeViewModel.anime.observeAsState(listOf())

    var to by remember { mutableStateOf("") }
    var anime: Anime? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf("") }

    val context = LocalContext.current

    //TODO: Autocomplete Input field for Anime (Modify the component)
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
            AutoCompleteBox(
                query = name,
                queryLabel = "Anime",
                predictions = animeList.filter {
                    it.anime.name.toLowerCase(Locale.current).contains(name.toLowerCase(Locale.current))
                },
                onQueryChanged = { name = it },
                onClearClick = { name = "" },
                onItemClick = {
                    anime = it.anime
                    name = it.anime.name
                },
                itemContent = {
                    DetailElementItem(animeData = it)
                }
            )
            Button(
                onClick = { navController.navigate(AniShareScreen.AddItem.name) },
            ) {
                Text(text = "Add Anime")
            }
            Divider()
            Button(
                onClick = {
                    userViewModel.launchEvent(UserDataEvent.AddItem(
                        UserData(
                            fromUser = "self",
                            toUser = to,
                            item = anime?.id!!
                        )
                    ))
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