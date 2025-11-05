package com.mobbelldev.erataniassessmenttest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobbelldev.erataniassessmenttest.presentation.component.TopAppBarComponent
import com.mobbelldev.erataniassessmenttest.presentation.screen.root.RootScreen
import com.mobbelldev.erataniassessmenttest.presentation.screen.word_search.WordSearchScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val title = when (currentDestination) {
        Screen.Root::class.qualifiedName -> "Eratani Assessment Test"
        Screen.WordSearch::class.qualifiedName -> "Pencarian Kata"
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
        }
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
                    }
                )
            }

            composable<Screen.WordSearch> {
                WordSearchScreen()
            }
        }
    }
}