package com.timife.domain.use_cases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.timife.domain.Resource
import com.timife.domain.model.Brand
import com.timife.domain.repositories.BrandRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBrandsTest {
    private lateinit var repository: BrandRepository
    private lateinit var getBrands: GetBrands

    @Before
    fun setUp() {
        repository = BrandFakeRepo()
        getBrands = GetBrands(
            repository
        )
    }

    @Test
    fun `fetch brands list`() = runTest {
        val data = (1..10).map {
            Brand(
                id = it,
                brand = "brand$it"
            )
        }

        getBrands.invoke(true).test {
            val brandList = awaitItem()

            assertThat(brandList is Resource.Success).isTrue()
            assertThat(brandList.data).isEqualTo(data)
            awaitComplete()
        }
    }
}