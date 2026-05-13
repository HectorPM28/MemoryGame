package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.example.project.viewModels.MemoryViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeAppCommonTest {

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

    @Test
    fun checkSetDifficultyValue() {
        viewModel.setDifficultyText("Easy")
        assertEquals("Easy", viewModel.difficultyValue.value)
    }

    @Test
    fun checkSetImagetextValue() {
        viewModel.setImageText("Mii")
        assertEquals("Mii", viewModel.imageTextValue.value)
    }

    @Test
    fun checkSetUsernameValue() {
        viewModel.setUsernameText("Hector")
        assertEquals("Hector", viewModel.userNameValue.value)
    }

    @Test
    fun checkSetPointsValue() {
        viewModel.setPointsLong(7L)
        assertEquals(7L, viewModel.pointValue.value)
    }

    @Test
    fun checkSetErrorValue() {
        viewModel.setErrorsLong(5L)
        assertEquals(5L, viewModel.errorValue.value)
    }
}