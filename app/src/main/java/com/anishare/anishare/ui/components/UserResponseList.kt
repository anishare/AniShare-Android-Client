package com.anishare.anishare.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.anishare.anishare.ui.data.UserViewModel

@Composable
fun UserResponseList(
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val userData by userViewModel.userData.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dashboard") })
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