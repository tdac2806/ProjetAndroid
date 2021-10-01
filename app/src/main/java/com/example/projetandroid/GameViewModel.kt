package com.example.projetandroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel(){
    private var  _score = 0
    private var _question = 0
    val score: Int
        get() = _score
    val question: Int
        get() = _question


    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord : LiveData<String>
        get() = _currentScrambledWord

    private var allWordsList: MutableList<String> = mutableListOf("Siamois","Persan",
        "Angora","Main coon","sacré de Birmanie", "Sphinx", "Bleu")

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            increaseQuestion()
            _currentScrambledWord.value = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        _question = 0
        wordsList.clear()
        getNextWord()
    }

    private fun increaseScore() {
        _score += 1
    }

    fun increaseQuestion() {
        _question += 1
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(currentWord, true)) {
            increaseScore()
            true
        } else false
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < allWordsList.size) {
            getNextWord()
            true
        } else false
    }
}
