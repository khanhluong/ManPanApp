package com.hk.manpan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hk.manpan.utils.Constants.TABLE_CARD_ENTRY

@Entity(tableName = TABLE_CARD_ENTRY)
class CardEntryEntity(
    val pan: String,
    val cvv: String,
    val expiry: String,
    // 0 for single, 1 for Recurring
    val motoType: Byte,
    val isCardStoreOnFile: Boolean,
    val noCVVReason: String,
    val amount: Double
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}