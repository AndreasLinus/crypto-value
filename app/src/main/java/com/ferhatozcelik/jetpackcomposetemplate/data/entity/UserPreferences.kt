package com.ferhatozcelik.jetpackcomposetemplate.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ferhatozcelik.jetpackcomposetemplate.util.USER_PREFERENCES_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = USER_PREFERENCES_TABLE_NAME)
data class UserPreferences(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String?,
    val description: String?,
    val isUsdCurrency: Boolean = true
) : Parcelable


