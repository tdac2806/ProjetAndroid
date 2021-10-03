package com.example.projetandroid


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(val playerNameList: Array<String>, val playerScoreList: Array<String>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val limit = 4

    // Describes an item view and its place within the RecyclerView
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerRank: TextView = itemView.findViewById(R.id.player_rank)
        private val playerName: TextView = itemView.findViewById(R.id.player_name)
        private val playerScore: TextView = itemView.findViewById(R.id.player_score)

        fun bind(position: Int, name: String, score: String) {
            playerRank.text = (position+1).toString()
            playerName.text = name
            playerScore.text = score
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)

        return PlayerViewHolder(view)
    }

    // Returns size of data list
    /*override fun getItemCount(): Int {
        return playerNameList.size
    }*/

    override fun getItemCount(): Int {
        return if (playerNameList.size > limit) {
            limit
        } else {
            playerNameList.size
        }
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(position, playerNameList[position],playerScoreList[position])
    }

}