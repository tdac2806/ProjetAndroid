package com.example.projetandroid

import android.content.Context

class Datasource(val context: Context) {
    fun getPlayerNameList(): Array<String> {
        // Return player name list from string resources
        return context.resources.getStringArray(R.array.player_name_array)
    }

    fun getPlayerScoreList(): Array<String> {
        // Return player score list from string resources
        return context.resources.getStringArray(R.array.player_score_array)
    }
}