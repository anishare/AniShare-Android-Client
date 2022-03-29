package com.anishare.anishare.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anishare.anishare.R
import com.anishare.anishare.ui.login.AuthViewModel
import com.anishare.anishare.util.LoadingState

@Composable
fun TopAppBar(
    authViewModel: AuthViewModel = viewModel(),
    title: @Composable () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 1.dp,
            title = title,
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            },
            actions = {
                if (authViewModel.isSignedIn()) {
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
                        authViewModel.signOut()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_login_24),
                            contentDescription = "Logout"
                        )
                    }
                }
            }
        )
        if (authViewModel.loadingState.value.status == LoadingState.Status.RUNNING) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}