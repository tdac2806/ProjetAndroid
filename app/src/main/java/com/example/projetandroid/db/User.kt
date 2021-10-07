package com.example.projetandroid.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "IsAdmin") val IsAdmin: Boolean,
    @ColumnInfo(name = "score") val score: Int = 0,
    @ColumnInfo(name = "maxscore") val maxscore: Int = 0
)
{
    @PrimaryKey(autoGenerate = true) var uuid : Int? = null
}