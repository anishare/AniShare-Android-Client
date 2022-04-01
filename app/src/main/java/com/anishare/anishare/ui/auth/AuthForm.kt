package com.anishare.anishare.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anishare.anishare.ui.components.TopAppBar
import com.anishare.anishare.ui.data.AuthViewModel
import com.anishare.anishare.util.AuthFormType
import com.anishare.anishare.util.LoadingState

@Composable
fun AuthForm(authFormType: AuthFormType, viewModel: AuthViewModel = viewModel()) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.loadingState.observeAsState()

    val enableButton =
        if (authFormType == AuthFormType.Login) email.isNotEmpty() && password.isNotEmpty()
        else email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = authFormType.name) })
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "Password")
                }
            )
            if (authFormType == AuthFormType.SignUp) {
                Spacer(modifier = Modifier.size(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = {
                        Text(text = "Confirm Password")
                    }
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = enableButton,
                onClick = {
                    when (authFormType) {
                        AuthFormType.Login -> viewModel.signIn(email.trim(), password.trim())
                        AuthFormType.SignUp -> viewModel.signUp(email.trim(), password.trim())
                    }
                }
            ) {
                Text(text = authFormType.name)
            }
        }
        when (state.value?.status) {
            LoadingState.Status.SUCCESS -> LaunchedEffect(state.value?.status) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                viewModel.setLoadingState(LoadingState.IDLE)
            }
            LoadingState.Status.FAILED -> LaunchedEffect(state.value?.status) {
                Toast.makeText(context, state.value?.msg, Toast.LENGTH_SHORT).show()
                viewModel.setLoadingState(LoadingState.IDLE)
            }
            else -> {}
        }
    }
}