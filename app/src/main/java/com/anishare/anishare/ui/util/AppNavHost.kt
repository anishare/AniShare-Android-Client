package com.anishare.anishare.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anishare.anishare.ui.components.UserResponseList
import com.anishare.anishare.ui.login.Login
import com.anishare.anishare.util.AniShareScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AniShareScreen.Dashboard.name,
        modifier = modifier
    ) {
        composable(AniShareScreen.Dashboard.name) {
//            OverviewBody(
//                onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
//                onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
//                onAccountClick = { name ->
//                    navigateToSingleAccount(navController, name)
//                }
//            )
            UserResponseList()
        }
        composable(AniShareScreen.Login.name) {
            Login()
        }
    }
}