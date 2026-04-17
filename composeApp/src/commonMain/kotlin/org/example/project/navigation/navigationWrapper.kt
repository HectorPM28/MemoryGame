package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.screens.DifficultyScreen
import org.example.project.screens.GameScreen
import org.example.project.screens.MenuScreen
import org.example.project.screens.ResultsScreen
import org.example.project.viewModels.memoryViewModel

@Composable
fun NavigationWrapper(){
    val viewModel = memoryViewModel()
    val backStack = rememberNavBackStack(navConfig, Route.MenuScreen)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.MenuScreen> {
                MenuScreen(
                    navigateToDifficulty = { backStack.add(Route.DifficultyScreen) },
                    memoryViewModel = viewModel
                )
            }
            entry<Route.DifficultyScreen> {
                DifficultyScreen(
                    navigateToGame = {backStack.add(Route.GameScreen)},
                    memoryViewModel = viewModel
                )
            }
            entry<Route.GameScreen> {
                GameScreen(navigateToResults = { backStack.add(Route.ResultsScreen) }, viewModel)
            }
            entry<Route.ResultsScreen> {
                ResultsScreen(navigateToMenu = { backStack.add(Route.MenuScreen) }, viewModel)
            }
        }
    )
}