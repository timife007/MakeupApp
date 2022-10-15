package com.timife.makeup.data.cache.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.timife.cache.model.MakeupItemEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@ExperimentalCoroutinesApi
class MakeupItemDaoTest {

    //Helps avoid concurrency, run each process one after the other.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: com.timife.cache.database.MakeupDatabase
    private lateinit var dao: com.timife.cache.database.MakeupItemDao

    @BeforeEach
    fun setUp() {
        //an in memory database that is refreshed when process is killed, runs on main thread
        //because this is a test case.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            com.timife.cache.database.MakeupDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.makeupItemDao
    }

    @AfterEach
    fun tearDown() {
        database.close()
    }

    @org.junit.jupiter.api.Test
    fun insertMakeupItem() = runTest {
        //Given
        val makeupItem = MakeupItemEntity(
            id = 1,
            name = "Lip Gloss",
            imageUrl = "https:github.com",
            productType = "liner",
            brand = "mascara",
            priceSign = "$",
            description = "A makeup Item",
            price = "25",
            currency = "CAD",
            rating = 2.0
        )
        val makeupList = listOf(makeupItem)
        dao.insertMakeupItems(makeupList)

        //When
        val allMakeupItems = dao.getMakeupItems()

        //Then
        assertThat(allMakeupItems).contains(makeupItem)
    }

    @Test
    fun clearMakeupItems() = runTest {
        //Given
        val makeupItem = MakeupItemEntity(
            id = 1,
            name = "Lip Gloss",
            imageUrl = "https:github.com",
            productType = "liner",
            brand = "mascara",
            priceSign = "$",
            description = "A makeup Item",
            price = "25",
            currency = "CAD",
            rating = 2.0
        )
        val makeupList = listOf(makeupItem)
        dao.insertMakeupItems(makeupList)
        dao.clearAllMakeupItems()

        //When
        val allMakeupItems = dao.getMakeupItems()

        //Then
        assertThat(allMakeupItems).doesNotContain(makeupItem)
    }

    @Test
    fun getAllMakeupItems() = runTest {
        //Given
        val makeupItem1 = MakeupItemEntity(
            id = 1,
            name = "Lip Gloss",
            imageUrl = "https:github.com",
            productType = "liner",
            brand = "mascara",
            priceSign = "$",
            description = "A makeup Item",
            price = "25",
            currency = "CAD",
            rating = 2.0
        )
        val makeupItem2 = MakeupItemEntity(
            id = 2,
            name = "Lip Stick",
            imageUrl = "https:github.com/timife007",
            productType = "liner",
            brand = "mascara",
            priceSign = "$",
            description = "A makeup Item",
            price = "25",
            currency = "CAD",
            rating = 2.0
        )
        val makeupList = listOf(makeupItem1,makeupItem2)
        dao.insertMakeupItems(makeupList)

        //When
        val makeupItems = dao.getMakeupItems()

        //Then
        assertThat(makeupItems).containsExactlyElementsIn(makeupList)
    }
}