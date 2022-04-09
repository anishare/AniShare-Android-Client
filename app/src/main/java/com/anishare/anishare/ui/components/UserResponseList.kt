package com.anishare.anishare.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anishare.anishare.ui.data.UserViewModel
import com.anishare.anishare.util.AniShareScreen

@Composable
fun UserResponseList(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val userData by userViewModel.userData.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dashboard") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AniShareScreen.AddItem.name)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(items = userData) { item ->
                ElementItem(data = item)
            }
        }
    }
}