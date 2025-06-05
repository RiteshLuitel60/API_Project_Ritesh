package com.example.api_project_ritesh.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.api_project_ritesh.data.model.LoginResponse
import com.example.api_project_ritesh.data.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val repository: ApiRepository = mock()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success updates loginResult with success`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPass"
        val response = LoginResponse("testKeypass")
        whenever(repository.login(username, password)).thenReturn(Result.success(response))

        // When
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val result = viewModel.loginResult.value
        assertTrue(result?.isSuccess == true)
        assertEquals(response, result?.getOrNull())
        assertFalse(viewModel.isLoading.value == true)
    }

    @Test
    fun `login failure updates loginResult with error`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPass"
        val error = Exception("Login failed")
        whenever(repository.login(username, password)).thenReturn(Result.failure(error))

        // When
        viewModel.login(username, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val result = viewModel.loginResult.value
        assertTrue(result?.isFailure == true)
        assertEquals(error, result?.exceptionOrNull())
        assertFalse(viewModel.isLoading.value == true)
    }

    @Test
    fun `login sets loading state correctly`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPass"
        val response = LoginResponse("testKeypass")
        whenever(repository.login(username, password)).thenReturn(Result.success(response))

        // When
        viewModel.login(username, password)
        assertTrue(viewModel.isLoading.value == true)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertFalse(viewModel.isLoading.value == true)
        verify(repository).login(username, password)
    }
} 