package com.example.projetandroid.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao {
    @Query("SELECT name FROM cat")
    fun getAllCat(): List<String>

    @Query("DELETE FROM cat")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM cat")
    fun countCat():Int

    @Query("SELECT COUNT(*) FROM cat WHERE LOWER(name) = LOWER(:name)")
    fun catExists(name:String):Int

    @Insert
    fun insert(vararg cat: Cat)

    @Query("DELETE FROM cat WHERE LOWER(name) = LOWER(:name)")
    fun delete(name:String)
}