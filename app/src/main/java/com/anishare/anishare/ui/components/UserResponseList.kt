package com.anishare.anishare.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anishare.anishare.ui.data.UserViewModel

@Composable
fun UserResponseList(userViewModel: UserViewModel = viewModel()) {
    val userData by userViewModel.userData.observeAsState(listOf())
    LazyColumn {
        items(items = userData) { item ->
            ElementItem(data = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserResponseList() {
    UserResponseList()
}