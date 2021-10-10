package com.hk.manpan.features.moto_trans.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hk.manpan.R
import com.hk.manpan.launchFragmentInHiltContainer
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MotoTransFragmentTest {


    @Test
    fun testInputMotoTrans_credit_card_empty() {
        launchFragmentInHiltContainer<MotoTransFragment>()
        onView(allOf(withId(R.id.card_entry_ed_enter_card), withText("")))
//        onView(allOf(withId(R.id.card_entry_ed_expiry_date), withText("1223")))
//        onView(allOf(withId(R.id.card_entry_ed_security_code), withText("123")))
        onView(withId(R.id.card_entry_btn_continue)).perform(ViewActions.click())
        onView(allOf(withId(R.id.card_entry_ed_enter_card), hasErrorText("Input error")))
    }

    @Test
    fun testInputMotoTrans_credit_card_not_length() {
        launchFragmentInHiltContainer<MotoTransFragment>()
        onView(allOf(withId(R.id.card_entry_ed_enter_card), withText("123")))
//        onView(allOf(withId(R.id.card_entry_ed_expiry_date), withText("1223")))
//        onView(allOf(withId(R.id.card_entry_ed_security_code), withText("123")))
        onView(withId(R.id.card_entry_btn_continue)).perform(ViewActions.click())
        onView(allOf(withId(R.id.card_entry_ed_enter_card), hasErrorText("Input error")))
    }

    @Test
    fun testInputMotoTrans_credit_card_correct() {
        launchFragmentInHiltContainer<MotoTransFragment>()
        onView(allOf(withId(R.id.card_entry_ed_enter_card), withText("1234123412341234")))
        onView(allOf(withId(R.id.card_entry_ed_expiry_date), withText("1223")))
        onView(allOf(withId(R.id.card_entry_ed_security_code), withText("123")))
        onView(withId(R.id.card_entry_btn_continue)).perform(ViewActions.click())
        onView(allOf(withId(R.id.card_entry_ed_enter_card), not(hasErrorText("Input error"))))
    }

}