package com.mb.app.data.offline

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    fun buildDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
                ).build()
    }
}