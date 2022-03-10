package com.stathis.unipiapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement

@Database(entities = [DeptAnnouncement::class] , version = 1, exportSchema = false)
abstract class UnipiDatabase : RoomDatabase() {

    abstract fun announcementDao(): UnipiDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UnipiDatabase? = null

        fun getDatabase(context: Context): UnipiDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UnipiDatabase::class.java,
                    "announcements_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}