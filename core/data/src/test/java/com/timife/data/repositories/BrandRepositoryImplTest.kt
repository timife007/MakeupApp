package com.timife.data.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.cache.Cache
import com.timife.cache.CacheFake
import com.timife.cache.model.MakeupBrandEntity
 import com.timife.data.mappers.toMakeupBrand
import com.timife.domain.Resource
import com.timife.remote.Remote
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class BrandRepositoryImplTest {

    private lateinit var repository: BrandRepositoryImpl
    private lateinit var remote: Remote
    private lateinit var cache: Cache

    @Before
    fun setUp() {
        cache = CacheFake()
        remote = mockk(relaxed = true) {
            coEvery {
                getRemoteMakeupBrands()
            } returns mockk(relaxed = true)
        }
        repository = BrandRepositoryImpl(
            cache = cache,
            remote = remote
        )
    }


    @Test
    fun `Test local database cache with remote set to true`() = runTest {
        val localBrands = listOf(
            MakeupBrandEntity(
                id = 1,
                brand = "Eyeliner"
            )
        )

        cache.insertBrand(localBrands)
        repository.getBrands(true).test {
            val loading = awaitItem()
            assertThat((loading as Resource.Loading).isLoading).isTrue()

            val items = awaitItem()

            assertThat((items is Resource.Success)).isTrue()
            assertThat(items.data).isEqualTo(
                localBrands.map {
                    it.toMakeupBrand()
                }
            )

            val remoteItems = awaitItem()
            assertThat((items is Resource.Success)).isTrue()

            assertThat(remoteItems.data).isEqualTo(
                cache.getLocalMakeupBrands().map {
                    it.toMakeupBrand()
                }
            )

            val finishLoading = awaitItem()
            assertThat((finishLoading as Resource.Loading).isLoading).isFalse()
            awaitComplete()
        }
    }
}