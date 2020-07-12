package com.naren.androidroom.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naren.androidroom.MainActivity
import com.naren.androidroom.R
import com.naren.androidroom.WriteActivity
import com.naren.androidroom.database.Entry
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class EntryListAdapter internal constructor(val context: Context)
    : RecyclerView.Adapter<EntryListAdapter.ViewHolder>(){

    private var entries = emptyList<Entry>()

    internal fun setWords(entries : List<Entry>){
            this.entries = entries
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: EntryListAdapter.ViewHolder, position: Int) {
        var item = entries[position]
        var id = item.id
        holder.title.text = item.title
        holder.desc.text = item.desc

        holder.delete.setOnClickListener {
            val entry = Entry(id,item.title,item.desc)
            (context as MainActivity).deleteEntry(entry)
            notifyDataSetChanged()
        }

        holder.edit.setOnClickListener{
            var intent = Intent(context,WriteActivity::class.java)
            intent.putExtra("isUpdate",true)
            intent.putExtra("id",item.id)
            context.startActivity(intent)
            notifyDataSetChanged()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title : TextView = view.txtTitle
        var desc : TextView = view.txtDesc
        var delete : ImageButton = view.btnRemove
        var edit : ImageButton = view.btnEdit
    }

}