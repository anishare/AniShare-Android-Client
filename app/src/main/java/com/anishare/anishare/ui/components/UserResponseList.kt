package com.anishare.anishare.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anishare.anishare.model.UserResponse

@Composable
fun UserResponseList(listResponse: MutableList<UserResponse>) {
    LazyColumn {
        items(items = listResponse) { item ->
            ElementItem(data = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserResponseList() {
    UserResponseList(listResponse = mutableListOf(
        UserResponse.mock(),
        UserResponse.mock(),
        UserResponse.mock()
    ))
}