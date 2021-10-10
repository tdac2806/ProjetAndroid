package com.example.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(val catNameList: Array<String>) :
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catName: TextView = itemView.findViewById(R.id.cat_name)

        fun bind(name: String) {
            catName.text = name
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)

        return CatViewHolder(view)
    }


    override fun getItemCount(): Int {
        return catNameList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(catNameList[position])
    }

}