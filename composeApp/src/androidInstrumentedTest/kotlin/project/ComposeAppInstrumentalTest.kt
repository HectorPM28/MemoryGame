package org.example.project

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.example.project.screens.DifficultyScreen
import org.example.project.viewModels.MemoryViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ComposeAppInstrumentalTest {
    private lateinit var viewModel: MemoryViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MemoryViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testSelectAllDifficulties() = runComposeUiTest {
        setContent {
            DifficultyScreen(
                navigateToGame = {},
                memoryViewModel = viewModel
            )
        }

        val difficulties = listOf("Easy", "Medium", "Hard")

        difficulties.forEach { level ->
            onNodeWithTag(level).assertIsDisplayed()

            onNodeWithTag(level).performClick()

            assertEquals("El ViewModel no cambió a $level", level, viewModel.difficulty)
        }
    }
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testStartGameButtonTriggersNavigation() = runComposeUiTest {
        var navigated = false

        setContent {
            DifficultyScreen(
                navigateToGame = { navigated = true },
                memoryViewModel = viewModel
            )
        }

        val startButton = onNodeWithText("Start game")

        startButton.assertIsDisplayed()
        startButton.performClick()

        assert(navigated) { "El click en 'Start game' no llamó a navigateToGame" }
    }
}