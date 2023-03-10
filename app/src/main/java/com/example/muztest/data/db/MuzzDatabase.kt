package com.example.muztest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.muztest.data.db.dao.MuzzDao
import com.example.muztest.data.db.entities.MessageEntity

@Database(
    entities = [
        MessageEntity::class
    ],
    version = MuzzDatabase.VERSION,
)
abstract class MuzzDatabase : RoomDatabase() {
    abstract val muzzDao: MuzzDao

    companion object {
        const val NAME = "muzz_test.db"

        const val VERSION = 1

        private val MIGRATIONS = emptyArray<Migration>()

        fun Builder<MuzzDatabase>.configure(): Builder<MuzzDatabase> = apply {
            addMigrations(*MIGRATIONS)
        }
    }
}