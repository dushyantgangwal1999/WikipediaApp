package com.example.wikithree.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikifive.R

import com.example.wikithree.model.Page
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val pages:ArrayList<Page>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>(){
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(page:Page){
            itemView.title_textView.text=page.title
            itemView.decription_textView.text= page.terms?.description?.get(0) ?: "Test"
            // Setting Image Height And Width
//            itemView.image_view.width= page.thumbnail?.width!!
            val uri=page.thumbnail?.source.toString()
            Glide.with(itemView.image_view.context)
                .load(Uri.parse(uri))
                .into(itemView.image_view)
            Log.d("Uri",page.thumbnail?.source.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.DataViewHolder {
       return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
        return holder.bind(pages[position])
    }

    override fun getItemCount(): Int {
        return pages.size
    }
    fun addPages(list: List<Page>){
        pages.addAll(list)
    }
}