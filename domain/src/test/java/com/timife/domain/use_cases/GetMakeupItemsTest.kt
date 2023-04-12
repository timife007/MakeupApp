package com.timife.domain.use_cases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.domain.Resource
import com.timife.domain.model.MakeupItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMakeupItemsTest {
    private lateinit var repository: MakeupItemFakeRepo
    private lateinit var getMakeupItems: GetMakeupItems

    @Before
    fun setUp() {
        repository = MakeupItemFakeRepo()
        getMakeupItems = GetMakeupItems(
            repository
        )
    }

    @Test
    fun `get filtered makeup items`() = runTest{
        val filtered = listOf(
            MakeupItem(
                id = 1,
                "",
                "",
                "",
                "",
                "",
                2.0,
                "brand1",
                "",
                ""
            )

        )

        getMakeupItems.invoke(true,"brand1").test {
            val list = awaitItem()
            assertThat((list is Resource.Success)).isTrue()
            assertThat(list.data).isEqualTo(filtered)
            awaitComplete()
        }
    }
}