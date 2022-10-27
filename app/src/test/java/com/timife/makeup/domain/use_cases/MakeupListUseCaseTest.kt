
package com.timife.makeup.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.timife.domain.use_cases.GetMakeupItems
import com.timife.makeup.data.repositories.FakeMakeupItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class MakeupListUseCaseTest {
    private lateinit var makeupListUseCase: GetMakeupItems

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setup(){
        makeupListUseCase = GetMakeupItems(FakeMakeupItemsRepository())
    }





    @Test
    fun `get all makeup items from database`() = runTest {
//        makeupListUseCase.getAllItems().collect{
//        }
    }
}