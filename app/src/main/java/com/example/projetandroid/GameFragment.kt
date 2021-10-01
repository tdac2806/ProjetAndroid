package com.example.projetandroid

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.projetandroid.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: GameFragmentBinding

    private var allWordsList: MutableList<String> = mutableListOf("Siamois","Persan",
        "Angora","Main coon","sacré de Birmanie", "Sphinx", "Bleu")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        Log.d("GameFragment", "GameFragment created/re-created!")
        Log.d("GameFragment", "Word: ${viewModel.currentScrambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordCount}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Envoyer.setOnClickListener { onSubmitWord() }
        binding.Passer.setOnClickListener { onSkipWord() }
        // Update the UI
        binding.score.text = "0"
        binding.Question.text = "0"
        updateDataOnScreen()
    }

    private fun onSubmitWord() {
        val playerWord = binding.InputAnswer.text.toString()
        if (viewModel.isUserWordCorrect(playerWord)) {
            if (viewModel.nextWord()) {
                updateDataOnScreen()
            } else {
                scoreDialog()
            }
        }
    }

    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            updateDataOnScreen()
        } else {
            scoreDialog()
        }
    }

    private fun scoreDialog(){
        var title : String
        if(viewModel.score < allWordsList.size/2){
            title = "Pas de chance !"
        }
        else
        {
            title = "Bravo"
        }
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage("Ton score est : ${viewModel.score}")
            .setCancelable(false)
            .setPositiveButton("Rejouer") { _, _ ->
                restartGame()
            }
            .setNegativeButton("Quitter"){_,_ ->
                exitGame()
            }
            .show()
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
        binding.Mot.text = viewModel.currentScrambledWord.value
        binding.score.text = viewModel.score.toString()
        binding.Question.text = viewModel.question.toString()
    }
}
