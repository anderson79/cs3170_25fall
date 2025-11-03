package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class GameViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    // current unshuffled word
    private lateinit var currentWord: String

    // set of used words
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
    fun checkUseGuess() {
        if (userGuess.equals(currentWord)){
            // correct guess!
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // reset user guess anytime they guess
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        if (_uiState.value.currentWordCount == MAX_NO_OF_WORDS){
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true,
                    score = updatedScore,
                    isGuessedWordWrong = false
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc()
                )
            }
        }
    }

    private fun pickRandomWordAndShuffle(): String
    {
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            // forgot to add this, we need to add the word we picked to the set of used words
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(
            currentScrambledWord = pickRandomWordAndShuffle()
        )
    }

    init {
        resetGame()
    }

}