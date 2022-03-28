package com.anishare.anishare.ui.login

import com.microsoft.identity.client.AcquireTokenSilentParameters
import com.microsoft.identity.client.IAccount
import com.microsoft.identity.client.IMultipleAccountPublicClientApplication
import com.microsoft.identity.client.SilentAuthenticationCallback
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.client.exception.MsalUiRequiredException
import com.microsoft.identity.common.java.providers.oauth2.IDToken

class UserRepresentation private constructor() {
    private val accounts: MutableList<IAccount> = ArrayList()

    val displayName: String?
        get() {
            if (accounts.isEmpty()) {
                return null
            }
            val displayName = getB2CDisplayNameFromAccount(accounts[0])
            return displayName ?: getSubjectFromAccount(accounts[0])
        }

    val getToken: String?
        get() {
            if (accounts.isEmpty()) {
                return null
            }
            return accounts[0].idToken ?: "No token found"
        }

    val getEmail: String?
        get() {
            if (accounts.isEmpty()) {
                return null
            }
            val email = getB2CEmailFromAccount(accounts[0])
            return email ?: "No Email found"
        }

    val getUsername: String?
        get() {
            if (accounts.isEmpty()) return null
            return accounts[0].username
        }

    fun acquireTokenSilentAsync(
        multipleAccountPublicClientApplication: IMultipleAccountPublicClientApplication,
        policyName: String = "B2C_1_signin_signup",
        scopes: List<String?>? = listOf("https://anishare.onmicrosoft.com/1f6adc88-3dd2-431d-b64d-46052bbc37d3/Files.Read"),
        callback: SilentAuthenticationCallback
    ) {
        for (account in accounts) {
            if (policyName.equals(getB2CPolicyNameFromAccount(account), ignoreCase = true)) {
                val parameters = AcquireTokenSilentParameters.Builder()
                    .fromAuthority("https://anishare.b2clogin.com/tfp/anishare.onmicrosoft.com/B2C_1_signin_signup/")
                    .withScopes(scopes)
                    .forAccount(account)
                    .withCallback(callback)
                    .build()
                multipleAccountPublicClientApplication.acquireTokenSilentAsync(parameters)
                return
            }
        }
        callback.onError(
            MsalUiRequiredException(
                MsalUiRequiredException.NO_ACCOUNT_FOUND,
                "Account associated to the policy is not found."
            )
        )
    }

    fun signOutAsync(
        multipleAccountPublicClientApplication: IMultipleAccountPublicClientApplication,
        callback: IMultipleAccountPublicClientApplication.RemoveAccountCallback
    ) {
        Thread {
            try {
                for (account in accounts) {
                    multipleAccountPublicClientApplication.removeAccount(account)
                }
                accounts.clear()
                callback.onRemoved()
            } catch (e: MsalException) {
                callback.onError(e)
            } catch (e: InterruptedException) {
                // Unexpected.
            }
        }.start()
    }

    companion object {

        fun getB2CUsersFromAccount(account: IAccount): UserRepresentation {
            val b2CUserHashMap = HashMap<String?, UserRepresentation>()
            val subject = getSubjectFromAccount(account)
            var user = b2CUserHashMap[subject]
            if (user == null) {
                user = UserRepresentation()
                b2CUserHashMap[subject] = user
            }
            user.accounts.add(account)
            return user
        }

        fun getB2CUsersFromAccountList(accounts: MutableList<IAccount>): MutableList<UserRepresentation> {
            val b2CUserHashMap = HashMap<String?, UserRepresentation>()
            for (account in accounts) {
                val subject = getSubjectFromAccount(account)
                var user = b2CUserHashMap[subject]
                if (user == null) {
                    user = UserRepresentation()
                    b2CUserHashMap[subject] = user
                }
                user.accounts.add(account)
            }
            val users: MutableList<UserRepresentation> = ArrayList()
            users.addAll(b2CUserHashMap.values)
            return users
        }

        private fun getB2CPolicyNameFromAccount(account: IAccount): String? {
            return account.claims!!["tfp"] as String?
                ?: // Fallback to "acr" (for older policies)
                return account.claims!!["acr"] as String?
        }

        private fun getSubjectFromAccount(account: IAccount): String? {
            return account.claims!![IDToken.SUBJECT] as String?
        }

        private fun getB2CDisplayNameFromAccount(account: IAccount): String? {
            val displayName = account.claims!![IDToken.NAME] ?: return null
            return displayName.toString()
        }

        private fun getB2CEmailFromAccount(account: IAccount): String? {
            val id = account.claims!![IDToken.EMAIL] ?: return null
            return id.toString()
        }
    }
}