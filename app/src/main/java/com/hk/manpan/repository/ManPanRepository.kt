package com.hk.manpan.repository

import com.hk.manpan.data.local.dao.CardEntryDao
import com.hk.manpan.data.local.entity.CardEntryEntity
import javax.inject.Inject

class ManPanRepository @Inject constructor(private val cardEntryDao: CardEntryDao) {
    suspend fun getAllCardEntry() = cardEntryDao.getCardEntryList()
    suspend fun insertCardEntry(cardEntryEntity: CardEntryEntity) = cardEntryDao.insertCardEntry(cardEntryEntity)
}