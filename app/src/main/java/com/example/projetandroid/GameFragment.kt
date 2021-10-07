package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.GameFragmentBinding
import com.example.projetandroid.db.GameDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: GameFragmentBinding
    lateinit var db: GameDatabase
    private lateinit var WordList: List<String>
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase").build()
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Envoyer.setOnClickListener { onSubmitWord(view) }
        binding.Passer.setOnClickListener { onSkipWord(view) }
        // Update the UI
        binding.score.text = "0"
        binding.Question.text = "0"

        GlobalScope.launch {
            WordList = db.catDao().getAllCat()
            viewModel.initWordList(WordList)
            updateDataOnScreen()
        }

    }

    private fun onSubmitWord(view :View) {
        val playerWord = binding.InputAnswer.text.toString()
        if (viewModel.isUserWordCorrect(playerWord)) {
            if (viewModel.nextWord()) {
                updateDataOnScreen()
            } else {
                scoreDialog(view)
            }
        }
    }

    private fun onSkipWord(view :View) {
        if (viewModel.nextWord()) {
            updateDataOnScreen()
        } else {
            scoreDialog(view)
        }
    }

    private fun scoreDialog(view :View) {
        val score = viewModel.score
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(username = "Tristan",score = score)
        view.findNavController().navigate(action)
    }
    private fun restartGame() {
        viewModel.reinitializeData()
        updateDataOnScreen()
    }

    private fun exitGame() {
        activity?.finish()
    }

    private fun updateDataOnScreen() {
        binding.InputAnswer.setText("")
        binding.Mot.text = viewModel.currentScrambledWord
        binding.score.text = viewModel.score.toString()
        binding.Question.text = viewModel.question.toString()
    }
}
