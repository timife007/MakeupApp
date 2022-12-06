package com.timife.data.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.cache.Cache
import com.timife.cache.CacheFake
import com.timife.cache.model.MakeupItemEntity
import com.timife.data.mappers.toMakeupItem
import com.timife.domain.Resource
import com.timife.domain.repositories.MakeupItemsRepository
import com.timife.remote.Remote
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MakeupItemsRepositoryImplTest {


    private lateinit var remote: Remote
    private lateinit var cache: Cache
    private lateinit var repository: MakeupItemsRepositoryImpl

    @Before
    fun setUp() {
        cache = CacheFake()
        remote = mockk(relaxed = true) {
            coEvery {
                getRemoteMakeupItems()
            } returns mockk(relaxed = true)
        }
        repository = MakeupItemsRepositoryImpl(cache, remote)
    }

    @Test
    fun getMakeupItems() = runTest {
        cache.insertItem(localItems)

        repository.getMakeupItems(true,"hasp").test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()

            val newItems = awaitItem()
            assertThat((newItems is Resource.Success)).isTrue()
            assertThat(newItems.data).isEqualTo(localItems.map {
                it.toMakeupItem()
            })

            val makeupItems = awaitItem()
            assertThat(makeupItems is Resource.Success).isTrue()
            assertThat(makeupItems.data).isEqualTo(cache.getUnfilteredItems().map {
                it.toMakeupItem()
            })

            val stopLoading = awaitItem()
            assertThat((stopLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    @Test
    fun getAllMakeupItems() = runTest{
        cache.insertItem(localItems)

        repository.getAllMakeupItems().test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()

            val unfilteredItems = awaitItem()
            assertThat((unfilteredItems is Resource.Success)).isTrue()
            assertThat(unfilteredItems.data).isEqualTo(localItems.map {
                it.toMakeupItem()
            })

            val stopLoading = awaitItem()
            assertThat((stopLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }

    companion object{
         val localItems = listOf(
            MakeupItemEntity(
                1, "lashes", "", "", "", "hasp", "", "", "", 2.0
            )
        )
    }
}