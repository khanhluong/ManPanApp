package com.hk.manpan.features.moto_trans.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hk.manpan.features.MainActivity
import org.junit.Rule



class MotoTransFragmentTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

}