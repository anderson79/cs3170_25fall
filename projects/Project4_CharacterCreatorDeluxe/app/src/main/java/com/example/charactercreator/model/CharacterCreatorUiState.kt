package com.example.charactercreator.model

data class CharacterCreatorUiState(
    val currentCharacter: Character = Character(),
    val selectedClass: String = "",
    val isCustom: Boolean = false,
    val isDefault: Boolean = false,
    val areAllPointsSpent: Boolean = false
)
