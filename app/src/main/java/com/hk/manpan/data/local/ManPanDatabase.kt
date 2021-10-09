package com.hk.manpan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hk.manpan.BuildConfig
import com.hk.manpan.data.local.dao.CardEntryDao
import com.hk.manpan.data.local.entity.CardEntryEntity

@Database(
    entities = [CardEntryEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class ManPanDatabase : RoomDatabase() {
    abstract fun getCardEntryEntityDao(): CardEntryDao
}