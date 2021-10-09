package com.example.projetandroid.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY maxscore DESC")
    fun getAllUsers(): List<User>

    @Query("SELECT score FROM user WHERE LOWER(username) = LOWER(:name)")
    fun getUserScore(name: String): Int

    @Query("UPDATE user SET score = :score WHERE LOWER(username) = LOWER(:name)")
    fun updateUserScore(name: String,score: Int)

    @Query("SELECT maxscore FROM user WHERE LOWER(username) = LOWER(:name)")
    fun getUserMaxScore(name: String): Int

    @Query("UPDATE user SET maxscore = :score WHERE LOWER(username) = LOWER(:name)")
    fun updateUserMaxScore(name: String,score: Int)

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM user WHERE LOWER(username) = LOWER(:name)")
    fun userExists(name:String):Boolean

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM user WHERE LOWER(username) = LOWER(:name) AND password = :password")
    fun userPasswordOk(name:String,password:String):Boolean

    @Query("SELECT COUNT(*) FROM user")
    fun countUser():Int

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM user WHERE LOWER(username) = LOWER(:name) AND IsAdmin = 1")
    fun isUserAdmin(name:String):Boolean

    @Query("DELETE FROM user")
    fun deleteAll()

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}