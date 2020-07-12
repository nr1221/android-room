package com.naren.androidroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naren.androidroom.adapter.EntryListAdapter
import com.naren.androidroom.database.Entry
import com.naren.androidroom.database.EntryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EntryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = EntryListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        viewModel.allEntries.observe(this , Observer {
           it?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this , WriteActivity::class.java))
        }
    }

    fun deleteEntry(entry: Entry){
        viewModel.delete(entry)
    }
}