package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.FragmentResultBinding
import com.example.projetandroid.db.GameDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var username: String
    private var score: Int = 0
    lateinit var db: GameDatabase
    companion object {
        const val USERNAME = "username"
        const val SCORE = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            score = it.getInt(SCORE)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase").build()
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.score.text = score.toString()
        GlobalScope.launch {
            SetTitle()
        }

        return binding.root
    }
    private suspend fun SetTitle(){
        var title : String
        if(score < db.catDao().countCat()/2){
            title = "Pas de chance !"
        }
        else
        {
            title = "Bravo"
        }
        binding.ResultText.text = title
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            db.userDao().updateUserScore(username,score)
            val maxscore = db.userDao().getUserMaxScore(username)
            if(score > maxscore)
            {
                db.userDao().updateUserMaxScore(username,score)
            }
        }

        binding.InputPlay.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToGameFragment()
            view.findNavController().navigate(action)
        }

    }
}