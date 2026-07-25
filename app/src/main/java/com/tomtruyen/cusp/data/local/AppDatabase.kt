package com.tomtruyen.cusp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CheckInEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckInDao
}
