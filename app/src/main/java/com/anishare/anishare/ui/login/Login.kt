package com.anishare.anishare.ui.login

import android.content.ClipData
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anishare.anishare.R
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException

private const val TAG: String = "Login"

@Composable
fun Login() {
    var msalApplication: ISingleAccountPublicClientApplication? by remember {
        mutableStateOf(null)
    }
    var account: UserRepresentation? by remember {
        mutableStateOf(null)
    }
    val activity = LocalContext.current

    if (msalApplication == null) {
        PublicClientApplication.createSingleAccountPublicClientApplication(
            activity,
            R.raw.auth_config_b2c,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(application: ISingleAccountPublicClientApplication?) {
                    msalApplication = application

                    msalApplication!!.getCurrentAccountAsync(object :
                        ISingleAccountPublicClientApplication.CurrentAccountCallback {
                        override fun onAccountLoaded(activeAccount: IAccount?) {
                            account = UserRepresentation.getB2CUsersFromAccount(activeAccount!!)
                        }

                        override fun onAccountChanged(
                            priorAccount: IAccount?,
                            currentAccount: IAccount?
                        ) {
                            TODO("Not yet implemented")
                        }

                        override fun onError(exception: MsalException) {
                            Log.e(TAG, exception.message.toString())
                        }

                    })
                }

                override fun onError(exception: MsalException?) {
                    Log.e(TAG, exception?.message.toString())
                }
            }
        )
    }

    Surface(modifier = Modifier.fillMaxHeight()) {
        Column {
            Button(onClick = {
                val scopes =
                    arrayOf("https://anishare.onmicrosoft.com/1f6adc88-3dd2-431d-b64d-46052bbc37d3/Files.Read")
                msalApplication?.signIn(
                    activity.findActivity()!!,
                    null,
                    scopes,
                    object : AuthenticationCallback {
                        override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                            account =
                                UserRepresentation.getB2CUsersFromAccount(authenticationResult!!.account)
                            Log.i(TAG, account?.displayName!!)
                            print(account)
                        }

                        override fun onError(exception: MsalException?) {
                            Log.e(TAG, exception?.message.toString())
                        }

                        override fun onCancel() {
                            Log.e(TAG, "Cancelled")
                        }
                    }
                )
            }) {
                Text(text = "Login")
            }

            if (account != null) {
                Card(
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth()
                        .height(110.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp
                ) {
                    Surface {
                        Row(
                            Modifier
                                .padding(4.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxHeight()
                                    .weight(0.8f)
                            ) {
                                Text(
                                    text = account!!.displayName!!,
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold
                                )
                                ClickableText(
                                    text = AnnotatedString(account!!.getToken!!),
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier
                                        .background(
                                            Color.LightGray
                                        )
                                        .padding(4.dp),
                                    onClick = {
                                        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                                        clipboard.setPrimaryClip(ClipData.newPlainText("token", account!!.getToken!!))
                                    }
                                )
//                                Text(
//                                    text = account!!.getUsername!!,
//                                    style = MaterialTheme.typography.body1,
//                                    maxLines = 2,
//                                    overflow = TextOverflow.Ellipsis
//                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//private fun LoginFunction(
//    activity: AppCompatActivity?,
//    msalApplication: ISingleAccountPublicClientApplication?,
//    account: IAccount?
//) {
//
//}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}