package com.naren.androidroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Entry::class] , version = 1 , exportSchema = false)
public abstract class EntryRoomDatabase : RoomDatabase() {

    abstract fun entryDao() : EntryDao

    companion object{
        @Volatile
        private var INSTANCE : EntryRoomDatabase ?= null

        fun getDatabase(context: Context)
        : EntryRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                EntryRoomDatabase::class.java,
                "entry_database")
                    .build()
                INSTANCE = instance
                instance
            }

        }

    }

}