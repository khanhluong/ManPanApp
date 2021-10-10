package com.hk.manpan.features.moto_trans.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.hk.manpan.data.local.entity.CardEntryEntity
import com.hk.manpan.repository.ManPanRepository

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MotoTransViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(ManPanRepository::class.java)
    lateinit var viewModel: MotoTransViewModel

    @Before
    fun setUp() {
        viewModel = MotoTransViewModel(repository)
    }

    @Test
    fun cardEntryExpiryCheck_when_year_is_greater_and_month_is_greater(){
        //GIVEN a expiry date with year and month is greater that now
        var expiry = "11/22"
        // WHEN check expiry
        var result = viewModel.cardEntryExpiryCheck(expiry)
        // Then return valid
        assertThat(result).isEqualTo(true)


        //GIVEN a expiry date with year and month is lower that now
        expiry = "07/20"
        // WHEN check expiry
        result = viewModel.cardEntryExpiryCheck(expiry)
        // Then return valid
        assertThat(result).isEqualTo(false)


        //GIVEN a expiry date with year is greater month is lower that now
        expiry = "07/23"
        // WHEN check expiry
        result = viewModel.cardEntryExpiryCheck(expiry)
        // Then return valid
        assertThat(result).isEqualTo(true)

        //GIVEN a expiry date with year is equal month is lower that now
        expiry = "07/21"
        // WHEN check expiry
        result = viewModel.cardEntryExpiryCheck(expiry)
        // Then return valid
        assertThat(result).isEqualTo(false)

        //GIVEN a expiry date with year is lower month is greater that now
        expiry = "11/20"
        // WHEN check expiry
        result = viewModel.cardEntryExpiryCheck(expiry)
        // Then return valid
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun insertCardEntry_with_empty_all(){
        val cardEntryEntity = generateCardEntryEntity()
        val result = viewModel.insertCardEntry(cardEntryEntity)
        assertThat(result.value).isFalse()
    }

    fun generateCardEntryEntity(): CardEntryEntity {
        return CardEntryEntity("", "", "", 0, false, "", 0.0)
    }

}