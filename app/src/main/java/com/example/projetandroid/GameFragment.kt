package com.example.projetandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.projetandroid.databinding.GameFragmentBinding
import com.example.projetandroid.db.GameDatabase

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private val authviewModel: AuthViewModel by activityViewModels()
    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var db: GameDatabase
    private lateinit var WordList: List<String>
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        db = Room.databaseBuilder(requireContext(), GameDatabase::class.java, "GameDatabase")
            .allowMainThreadQueries().build()

        authviewModel.login.observe(viewLifecycleOwner,
            { newvalue ->
                username = newvalue.toString()
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Envoyer.setOnClickListener { onSubmitWord(view) }
        binding.Passer.setOnClickListener { onSkipWord(view) }
        // Update the UI
        binding.score.text = "0"
        binding.Question.text = "0"
        WordList = db.catDao().getAllCat()
        binding.TotalCat.text = WordList.size.toString()
        viewModel.initWordList(WordList)
        updateDataOnScreen()
    }

    private fun onSubmitWord(view: View) {
        val playerWord = binding.InputAnswer.text.toString()
        if (viewModel.isUserWordCorrect(playerWord)) {
            if (viewModel.nextWord()) {
                updateDataOnScreen()
            } else {
                scoreDialog(view)
            }
        }
    }

    private fun onSkipWord(view: View) {
        if (viewModel.nextWord()) {
            updateDataOnScreen()
        } else {
            scoreDialog(view)
        }
    }

    private fun scoreDialog(view: View) {
        val score = viewModel.score
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(
            username = username,
            score = score
        )
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}