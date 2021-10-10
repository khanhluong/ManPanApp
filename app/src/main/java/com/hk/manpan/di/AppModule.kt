package com.hk.manpan.di

import android.content.Context
import androidx.room.Room
import com.hk.manpan.data.local.ManPanDatabase
import com.hk.manpan.data.local.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideManPanDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ManPanDatabase::class.java, "man_pan_db").
            addMigrations(Utils.MIGRATION_1_2).
        build()

    @Singleton
    @Provides
    fun provideCardEntryDao(db: ManPanDatabase) = db.getCardEntryEntityDao()
}