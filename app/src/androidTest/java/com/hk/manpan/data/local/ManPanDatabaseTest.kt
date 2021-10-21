package com.hk.manpan.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth
import com.hk.manpan.data.local.dao.CardEntryDao
import com.hk.manpan.data.local.entity.CardEntryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class ManPanDatabaseTest {

    private lateinit var cardEntryDao: CardEntryDao
    private lateinit var db: ManPanDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ManPanDatabase::class.java).build()
        cardEntryDao = db.getCardEntryEntityDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertMotoTransToDatabase() = runBlocking {
        val manPanEntity = CardEntryEntity(
            "test", "123", "0320",
            motoType = false, isCardStoreOnFile = true, "", 200.0
        )
        cardEntryDao.insertCardEntry(manPanEntity)
        val getMotoResult = cardEntryDao.getCardEntryList()
        Truth.assertThat(getMotoResult.size).isEqualTo(1)
    }

    @Test
    fun insertMotoTransToDatabase_and_search() = runBlocking {
        val manPanEntity = CardEntryEntity(
            "test", "123", "0320",
            motoType = false, isCardStoreOnFile = true, "", 200.0
        )
        cardEntryDao.insertCardEntry(manPanEntity)
        val getMotoResult = cardEntryDao.getCardEntryList()
        Truth.assertThat(getMotoResult.size).isEqualTo(1)

        val searchResult = cardEntryDao.getTransactionWithValue(1)
        Truth.assertThat(searchResult.size).isEqualTo(1)
    }
}