package com.hk.manpan.data.local.utils

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Utils {

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //DO migration db in here
            }

        }
    }


}