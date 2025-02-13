package com.ferhatozcelik.jetpackcomposetemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ferhatozcelik.jetpackcomposetemplate.data.dao.UserPreferencesDao
import com.ferhatozcelik.jetpackcomposetemplate.data.entity.UserPreferences
import com.ferhatozcelik.jetpackcomposetemplate.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [UserPreferences::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getExampleDao(): UserPreferencesDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}