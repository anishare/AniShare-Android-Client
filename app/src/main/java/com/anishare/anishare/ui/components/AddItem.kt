package com.anishare.anishare.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.ui.data.UserViewModel
import com.anishare.anishare.ui.util.UserDataEvent
import java.util.*

@Composable
fun AddItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {

    var name by remember { mutableStateOf("") }
    var isAnime by remember { mutableStateOf(true) }

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
                value = name,
                singleLine = true,
                onValueChange = { name = it },
                label = {
                    Text(text = "Name")
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = isAnime, onClick = { isAnime = true })
                Text(
                    text = "Anime",
                    modifier = Modifier
                        .clickable(onClick = { isAnime = true })
                        .padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                RadioButton(selected = !isAnime, onClick = { isAnime = false })
                Text(
                    text = "Manga",
                    modifier = Modifier
                        .clickable(onClick = { isAnime = false })
                        .padding(start = 4.dp)
                )
            }
            Button(
                onClick = {
                    userViewModel.launchEvent(
                        UserDataEvent.AddItem(
                            UserData(
                                isAnime = isAnime,
                                dateCreated = Calendar.getInstance().time.toString(),
                                fromUser = "me",
                                toUser = "me",
                                isFinished = false,
                                item = Anime(
                                    name = name,
                                    malID = "1"
                                )
                            )
                        )
                    )
                },
                enabled = name.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add")
            }
        }
    }
}