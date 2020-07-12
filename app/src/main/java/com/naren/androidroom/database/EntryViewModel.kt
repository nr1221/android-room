package com.naren.androidroom.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application) : AndroidViewModel(application){

    private val repository : EntryRepository

    val allEntries : LiveData<List<Entry>>

     var entry : MutableLiveData<Entry> = MutableLiveData()

    fun getSingleEntry() : LiveData<Entry> = entry

    init {
        val entryDao = EntryRoomDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        allEntries = repository.allEntries
    }


    fun insert(entry: Entry) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(entry)
    }

    fun delete(entry: Entry) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(entry)
    }

    fun getSingle(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        entry.postValue(repository.getSingle(id))
    }

    fun updateEntry(entry : Entry) = viewModelScope.launch(Dispatchers.IO){
        repository.updateEntry(entry)
    }

}