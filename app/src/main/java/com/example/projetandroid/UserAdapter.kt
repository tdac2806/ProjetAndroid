package com.example.projetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val userNameList: Array<String>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.user_name)

        fun bind(name: String) {
            userName.text = name
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)

        return UserViewHolder(view)
    }


    override fun getItemCount(): Int {
        return userNameList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userNameList[position])
    }

}