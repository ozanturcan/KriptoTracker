package com.penguinlab.data.di.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.penguinlab.data.di.local.entity.ItemEntity


@Database(
    entities = [ItemEntity::class],
    version = 0,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase()