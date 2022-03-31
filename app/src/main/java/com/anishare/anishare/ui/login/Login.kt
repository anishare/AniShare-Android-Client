package com.anishare.anishare.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anishare.anishare.ui.components.TopAppBar
import com.anishare.anishare.util.LoadingState

@Composable
fun Login(viewModel: AuthViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.loadingState.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "Login") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "Password")
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 8.dp),
                enabled = email.isNotEmpty() && password.isNotEmpty(),
                onClick = {
                    viewModel.signIn(email.trim(), password.trim())
                }
            ) {
                Text(text = "Login")
            }
        }
        when (state.value?.status) {
            LoadingState.Status.SUCCESS -> LaunchedEffect(state.value?.status) {
                val res = scaffoldState.snackbarHostState.showSnackbar("Success")
                if (res == SnackbarResult.Dismissed) {
                    viewModel.setLoadingState(LoadingState.IDLE)
                }
            }
            LoadingState.Status.FAILED -> LaunchedEffect(state.value?.status) {
                val res = scaffoldState.snackbarHostState.showSnackbar(state.value?.msg!!)
                if (res == SnackbarResult.Dismissed) {
                    viewModel.setLoadingState(LoadingState.IDLE)
                }
            }
            else -> {}
        }
    }
}