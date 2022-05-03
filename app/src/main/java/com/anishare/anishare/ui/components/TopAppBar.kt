package com.anishare.anishare.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anishare.anishare.R
import com.anishare.anishare.ui.data.AuthViewModel
import com.anishare.anishare.util.LoadingState

@Composable
fun TopAppBar(
    authViewModel: AuthViewModel = hiltViewModel(),
    title: @Composable () -> Unit = {},
    loginNav: () -> Unit = {}
) {
    val loadingState = authViewModel.loadingState.observeAsState()
    val auth = authViewModel.isUserAuthenticated.observeAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 1.dp,
            title = title,
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = "Drawer"
                    )
                }
            },
            actions = {
                if (auth.value!!) {
                    IconButton(onClick = {
                        authViewModel.signOut()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ExitToApp,
                            contentDescription = "Logout"
                        )
                    }
                } else {
                    IconButton(onClick = {
                        loginNav()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_login_24),
                            contentDescription = "Login"
                        )
                    }
                }
            }
        )
        if (loadingState.value?.status == LoadingState.Status.RUNNING) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}