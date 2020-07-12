package com.naren.androidroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.naren.androidroom.database.Entry
import com.naren.androidroom.database.EntryViewModel
import kotlinx.android.synthetic.main.activity_write.*
import org.w3c.dom.Text
import java.util.*

class WriteActivity : AppCompatActivity() {

    private lateinit var viewModel: EntryViewModel
    private var isUpdate : Boolean = false
    var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        isUpdate = intent.getBooleanExtra("isUpdate",false)

        viewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        submitPost.setOnClickListener {
            savePost()
        }

        if(isUpdate){
            id = intent.getIntExtra("id",0)
            viewModel.getSingle(id)
            viewModel.getSingleEntry().observe(this , androidx.lifecycle.Observer {
                edTxtTitle.setText(it.title)
                edTxtDesc.setText(it.desc)
            })
        }

    }

   private fun savePost(){

        if (TextUtils.isEmpty(edTxtTitle.text) || TextUtils.isEmpty(edTxtDesc.text)){
            Toast.makeText(this , R.string.empty , Toast.LENGTH_LONG).show()
        }

        else{

            if(isUpdate){
                val entry = Entry(id,edTxtTitle.text.toString(), edTxtDesc.text.toString())
                viewModel.updateEntry(entry)
                Toast.makeText(this, R.string.update , Toast.LENGTH_LONG).show()
                refreshUi()
            }
            else {
                val entry = Entry(id,edTxtTitle.text.toString(), edTxtDesc.text.toString())
                viewModel.insert(entry)
                Toast.makeText(this, R.string.success , Toast.LENGTH_LONG).show()
                refreshUi()
            }
        }

    }

    private fun refreshUi(){
        edTxtTitle.setText("")
        edTxtDesc.setText(" ")
    }

}