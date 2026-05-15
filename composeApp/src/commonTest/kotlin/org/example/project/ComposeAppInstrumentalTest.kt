package org.example.project

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.example.project.screens.DifficultyScreen
import org.example.project.viewModels.MemoryViewModel
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.AfterTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ComposeAppInstrumentalTest {
    private lateinit var viewModel: MemoryViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MemoryViewModel()
    }

    @AfterTest
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
            onNodeWithTag(level)
                .assertExists()
                .performClick()

            waitForIdle()

            assertEquals(level, viewModel.difficulty, viewModel.difficulty)
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testStartGameButtonTriggersNavigation() = runComposeUiTest {
        // 1. Setup
        var navigated = false
        val viewModel = MemoryViewModel()

        setContent {
            DifficultyScreen(
                navigateToGame = { navigated = true },
                memoryViewModel = viewModel
            )
        }

        val startButton = onNodeWithText("Start game")

        startButton.assertIsDisplayed()
        startButton.performClick()

        waitForIdle()

        assertTrue(navigated, "La navegación no se activó al pulsar 'Start game'")
    }
}