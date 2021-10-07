package com.example.projetandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Cat::class], version = 1)
abstract class GameDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun catDao(): CatDao

}