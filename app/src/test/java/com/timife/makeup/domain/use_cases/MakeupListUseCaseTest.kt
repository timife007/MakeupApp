package com.timife.makeup.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.timife.makeup.data.repositories.FakeMakeupItemsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class MakeupListUseCaseTest {
    private lateinit var makeupListUseCase: MakeupListUseCase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setup(){
        makeupListUseCase = MakeupListUseCase(FakeMakeupItemsRepository())
    }





    @Test
    fun `get all makeup items from database`() = runTest {
        makeupListUseCase.getAllItems().collect{
        }
    }
}