package com.ferhatozcelik.jetpackcomposetemplate.data.dao

import androidx.room.*
import com.ferhatozcelik.jetpackcomposetemplate.data.entity.UserPreferences
import com.ferhatozcelik.jetpackcomposetemplate.util.USER_PREFERENCES_TABLE_NAME

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM $USER_PREFERENCES_TABLE_NAME")
    fun getUserPreferences(): List<UserPreferences>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: UserPreferences?)

    @Update
    suspend fun update(search: UserPreferences)

    @Delete
    suspend fun delete(search: UserPreferences)

}