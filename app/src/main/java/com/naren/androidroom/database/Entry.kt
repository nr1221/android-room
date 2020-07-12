package com.naren.androidroom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry_table")
data class Entry(
        @PrimaryKey(autoGenerate = true)
        var id : Int,

     @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "description") var desc : String
)