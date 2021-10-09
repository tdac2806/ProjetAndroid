package com.example.projetandroid.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat")
data class Cat (
    @ColumnInfo(name = "name") val name: String,
)
{
    @PrimaryKey(autoGenerate = true) var uuid : Int? = null
}