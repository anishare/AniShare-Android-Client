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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.anishare.anishare.domain.model.Anime
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.model.UserData
import com.anishare.anishare.ui.data.MALViewModel
import com.anishare.anishare.ui.data.UserViewModel
import com.anishare.anishare.ui.util.UserDataEvent
import com.anishare.anishare.util.AniShareScreen
import java.util.*

@Composable
fun AddItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    malViewModel: MALViewModel = hiltViewModel()
) {

    var name by remember { mutableStateOf("") }
    var isAnime by remember { mutableStateOf(true) }
    var selectedMALItem: AnimeMALNode? by remember { mutableStateOf(null) }

    val searchResult = malViewModel.searchResults(name).collectAsLazyPagingItems()

    val context = LocalContext.current

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
            Divider()
            Text(
                text = "Select an entry which closely relates to what you want to recommend",
                fontWeight = FontWeight.Bold,
            )
            LazyRow {
                items(items = searchResult) {
                    CardElementItem(
                        modifier = Modifier.padding(8.dp),
                        malNode = it,
                        onClick = { selectedMALItem = it },
                        selected = selectedMALItem?.node?.id == it?.node?.id
                    )
                }
            }
            selectedMALItem?.let {
                Text(text = "Selected item:")
                Text(text = "Name: ${it.node.title}")
            }
            Divider()
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
                                    malID = selectedMALItem?.node?.id ?: -1
                                )
                            )
                        )
                    )
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(AniShareScreen.Dashboard.name) {
                        popUpTo(AniShareScreen.Dashboard.name)
                    }
                },
                enabled = name.isNotEmpty() && selectedMALItem != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add")
            }
        }
    }
}