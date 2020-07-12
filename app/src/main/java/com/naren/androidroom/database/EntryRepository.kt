package com.naren.androidroom.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EntryRepository(private val entryDao: EntryDao) {

    val allEntries : LiveData<List<Entry>> = entryDao.getAll()
    
    suspend fun insert(entry: Entry){
        entryDao.insert(entry)
    }

    suspend fun delete(entry: Entry){
        entryDao.delete(entry)
    }

    fun getSingle(id : Int) : Entry{
       return entryDao.getSingle(id)
    }

    suspend fun updateEntry(entry: Entry){
        entryDao.updateEntry(entry)
    }
}