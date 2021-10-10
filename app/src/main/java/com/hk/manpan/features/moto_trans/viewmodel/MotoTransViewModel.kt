package com.hk.manpan.features.moto_trans.viewmodel

import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.hk.manpan.data.local.entity.CardEntryEntity
import com.hk.manpan.repository.ManPanRepository
import com.hk.manpan.utils.ManPanEntityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MotoTransViewModel @Inject constructor(private val repository: ManPanRepository) :
    ViewModel() {
    private val CARD_LENGTH = 16

    private val cardEntryEntities: MutableLiveData<List<CardEntryEntity>> by lazy {
        MutableLiveData<List<CardEntryEntity>>()
    }

    fun getNumberOfEntryCard(): LiveData<List<CardEntryEntity>> {
        viewModelScope.launch {
            cardEntryEntities.postValue(repository.getAllCardEntry())
        }
        return cardEntryEntities
    }

    fun insertCardEntry(cardEntryEntity: CardEntryEntity): LiveData<ManPanEntityStatus> {
        val checkInput: MutableLiveData<ManPanEntityStatus> = MutableLiveData()
        val manPanEntityStatus = ManPanEntityStatus();
        if (!cardEntryPanCheck(cardEntryEntity.pan)) {
            manPanEntityStatus.panStatus = false
        }
        if (!cardEntryExpiryCheck(cardEntryEntity.expiry)) {
            manPanEntityStatus.expiryStatus = false
        }
        if (checkInput.value?.expiryStatus == true && checkInput.value?.expiryStatus == true) {
            viewModelScope.launch {
                repository.insertCardEntry(cardEntryEntity)
            }
            checkInput.postValue(ManPanEntityStatus())
        }
        checkInput.postValue(manPanEntityStatus)
        return checkInput

    }

    private fun cardEntryPanCheck(pan: String): Boolean {
        val removeSpace = pan.replace(" ", "")
        return if (!TextUtils.isEmpty(removeSpace)) {
            removeSpace.length == CARD_LENGTH
        } else {
            false
        }
    }

    private fun cardEntryCvvCheck(cvv: String): Boolean {
        if (cvv.isNotEmpty() && cvv.toInt() > 0) {
            return true
        }
        return false
    }

    fun cardEntryExpiryCheck(expiry: String): Boolean {
        if (expiry.contains("/")) {
            val dateTimeExpiry = expiry.split("/")
            return when {
                dateTimeExpiry[1].toInt() > getMonthAndYearByPattern("yy").toInt() -> {
                    true
                }
                dateTimeExpiry[1].toInt() == getMonthAndYearByPattern("yy").toInt() -> {
                    dateTimeExpiry[0].toInt() >= getMonthAndYearByPattern("MM").toInt()
                }
                else -> {
                    false
                }
            }
        }
        return false
    }

    private fun cardEntryAmountCheck(amount: Double): Boolean {
        return true
    }

    /**
     * Get Month and Year current
     * using MM for month and YY for date.
     * return "" in case of input is empty
     */
    private fun getMonthAndYearByPattern(pattern: String): String {
        if (pattern.isNotEmpty()) {
            val dateFormat: DateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date = Date()
            return dateFormat.format(date)
        }
        return ""
    }
}