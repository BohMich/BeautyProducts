package com.mb.app.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntry::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): ProductDao

}