package com.example.projetandroid.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT score FROM user WHERE username = :name")
    fun getUserScore(name: String): Int

    @Query("UPDATE user SET score = :score WHERE username = :name")
    fun updateUserScore(name: String,score: Int)

    @Query("SELECT maxscore FROM user WHERE username = :name")
    fun getUserMaxScore(name: String): Int

    @Query("UPDATE user SET maxscore = :score WHERE username = :name")
    fun updateUserMaxScore(name: String,score: Int)

    @Query("SELECT username FROM user WHERE uuid = :UserId")
    fun getUserUsername(UserId: Int): Int

    @Query("DELETE FROM user")
    fun deleteAll()

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}