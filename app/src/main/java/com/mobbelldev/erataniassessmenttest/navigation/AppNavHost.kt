package com.mobbelldev.erataniassessmenttest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobbelldev.erataniassessmenttest.ui.component.TopAppBarComponent
import com.mobbelldev.erataniassessmenttest.ui.screen.animation.AnimationScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.APICallingScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.RegisterUserScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.UserListScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.DataProcessingScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.root.RootScreen
import com.mobbelldev.erataniassessmenttest.ui.screen.word_search.WordSearchScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }

    val title = when (currentDestination) {
        Screen.Root::class.qualifiedName -> "Eratani Assessment Test"
        Screen.WordSearch::class.qualifiedName -> "Pencarian Kata"
        Screen.DataProcessing::class.qualifiedName -> "Pemrosesan Data"
        Screen.Animation::class.qualifiedName -> "Animasi"
        Screen.APICalling::class.qualifiedName -> "API Calling"
        Screen.ListUser::class.qualifiedName -> "Menampilkan Daftar User"
        Screen.RegisterUser::class.qualifiedName -> "Daftar User"
        else -> ""
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                title = title,
                showBackButton = currentDestination != Screen.Root::class.qualifiedName,
                onBackClick = { navController.popBackStack() }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(paddingValues = innerPadding),
            navController = navController,
            startDestination = Screen.Root
        ) {
            composable<Screen.Root> {
                RootScreen(
                    moveToWordSearchScreen = {
                        navController.navigate(route = Screen.WordSearch)
                    },
                    moveToDataProcessingScreen = {
                        navController.navigate(route = Screen.DataProcessing)
                    },
                    moveToAnimation = {
                        navController.navigate(route = Screen.Animation)
                    },
                    moveToAPICalling = {
                        navController.navigate(route = Screen.APICalling)
                    },
                )
            }

            composable<Screen.WordSearch> {
                WordSearchScreen()
            }

            composable<Screen.DataProcessing> {
                DataProcessingScreen()
            }

            composable<Screen.Animation> {
                AnimationScreen()
            }

            composable<Screen.APICalling> {
                APICallingScreen(
                    moveToListUser = {
                        navController.navigate(route = Screen.ListUser)
                    },
                    moveToRegisterUser = {
                        navController.navigate(route = Screen.RegisterUser)
                    }
                )
            }

            composable<Screen.ListUser> {
                UserListScreen()
            }

            composable<Screen.RegisterUser> {
                RegisterUserScreen(snackbarHostState = snackbarHostState)
            }
        }
    }
}