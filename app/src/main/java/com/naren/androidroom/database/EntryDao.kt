package com.naren.androidroom.database

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface EntryDao {

    @Query("SELECT * FROM entry_table")
    fun getAll() : LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: Entry)

    @Delete
    suspend fun delete(entry: Entry)

    @Query("SELECT * FROM entry_table WHERE id=:id")
    fun getSingle(id : Int) : Entry

    @Update
    suspend fun updateEntry(entry: Entry)
}