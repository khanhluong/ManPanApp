package com.hk.manpan.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hk.manpan.data.local.entity.CardEntryEntity
import com.hk.manpan.utils.Constants

@Dao
interface CardEntryDao {

    @Query("SELECT * FROM " + Constants.TABLE_CARD_ENTRY)
    suspend fun getCardEntryList(): List<CardEntryEntity>

    //TODO: Fixme with strategy is replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardEntry(manPanEntity: CardEntryEntity)
}