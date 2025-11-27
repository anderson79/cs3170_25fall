package com.example.charactercreator.model

import androidx.lifecycle.ViewModel
import com.example.charactercreator.data.DataSource
import com.example.charactercreator.data.DataSource.defaultCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CharacterCreatorViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(CharacterCreatorUiState())
    val uiState = _uiState.asStateFlow()


    val areTextFieldsFilled: Boolean
        get() = !_uiState.value.currentCharacter.name.isEmpty() &&
                !_uiState.value.currentCharacter.charClass.isEmpty() &&
                !_uiState.value.currentCharacter.description.isEmpty()

    val areAllPointsSpent: Boolean
        get() = _uiState.value.currentCharacter.maxPoints == _uiState.value.currentCharacter.totalPoints
    /**
     * Helper function to update the given stat based on a String. The characterState.value is assigned
     * to a different Character if a change has occured, which will trigger recomposition
     *
     * @param characterState mutable state, the value field is reassigned which will trigger recomposition
     * @param statName String of the stat in statMap to be updated
     * @param delta how much (usually +/- 1) to change the stat by
     * @param maxPoints the max limit for the sum of points, so we don't go over
     */
    fun updateStat(
        statName: String, delta: Int, maxPoints: Int
    ) {
        val current = _uiState.value.currentCharacter
        val updated = current.withUpdatedStat(statName, delta, maxPoints)
        if (updated != current) {
            _uiState.update { currentState ->
                currentState.copy(currentCharacter = updated)
            }
        }
    }

    /**
     * Helper function to update the given stat based on an index. The characterState.value is assigned
     * to a different Character if a change has occured, which will trigger recomposition
     *
     * @param characterState mutable state, the value field is reassigned which will trigger recomposition
     * @param statIndex Index of the stat in statArray to be updated
     * @param delta how much (usually +/- 1) to change the stat by
     * @param maxPoints the max limit for the sum of points, so we don't go over
     */
    fun updateStat(
        statIndex: Int, delta: Int, maxPoints: Int
    ) {
        val current = _uiState.value.currentCharacter
        val updated = current.withUpdatedStat(statIndex, delta, maxPoints)
        if (updated != current) {
            _uiState.update { currentState ->
                currentState.copy(currentCharacter = updated)
            }
        }
    }


    /**
     * If you just want to update the entire stats array, you can pass the new one in here and
     * it will update the stats as well as the derived attributes
     *
     * @param newStatArray the new array of stats to be used
     */
    fun updateStats(
        newStatArray: Array<Int>
    ) {
        if (!_uiState.value.currentCharacter.statArray.equals(newStatArray)) { // only update if we need to
            _uiState.update { currentState ->
                currentState.copy(
                    currentCharacter =
                        currentState.currentCharacter.copy(
                            statArray = newStatArray.clone(),
                            attribArray = Character.computeAttributes(newStatArray)
                        )
                )
            }

            // also update the statMap
            updateStats(
                DataSource.mapFromArray(
                    statArray = newStatArray,
                    statInfoList = DataSource.statList
                )
            )
        }
    }

    fun updateStats(
        newStatMap: Map<String, Int>
    ) {
        if (!_uiState.value.currentCharacter.statMap.equals(newStatMap)) { // only update if we need to
            _uiState.update { currentState ->
                currentState.copy(
                    currentCharacter = currentState.currentCharacter.copy(
                        statMap = newStatMap,
                        attribMap = Character.computeAttributes(newStatMap)
                    )
                )
            }

            // also update the statArray
            updateStats(newStatMap.values.toTypedArray())
        }
    }

    fun onNameChange(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(currentState.currentCharacter.copy(name = newName))
        }
    }

    fun onClassChange(newClass: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentCharacter = currentState.currentCharacter.copy(
                    charClass = newClass

                )
            )
        }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { currentState ->
            currentState.copy(currentState.currentCharacter.copy(description = newDescription))
        }
    }

    private fun changeNameToDefault(classDefault: String) {
        // change the character's name
        val charNames = defaultCharacters.groupBy { it.charClass }
            .mapValues { (_, chars) -> chars.map { it.name } }

        onNameChange(
            newName = charNames.getOrDefault(
                key = classDefault, defaultValue = List(
                    size = 1, init = { "No name" })
            ).get(0)
        )
    }

    private fun changeStatsToDefault(classDefault: String) {
        val charStatMaps =
            defaultCharacters.associate { character -> character.charClass to character.statMap }
        //val charStatArrays = defaultCharacters.associate { it.charClass to it.statArray }

        for (character in defaultCharacters) {
            if (character.name.equals(classDefault)) {
                _uiState.value = _uiState.value.copy(currentCharacter = character)
            }
        }
    }

    fun onDefaultCharacterSelected(defaultClass: String) {
        val defaultCharsMap = defaultCharacters.associate { character ->
            character.charClass to character
        }

        _uiState.update { currentState ->
            currentState.copy(
                currentCharacter = defaultCharsMap.getOrDefault(
                    key = defaultClass,
                    defaultValue = DataSource.dummyChar
                )
            )
        }

    }

    fun onSelectedClassChange(newSelectedClass: String) {
        val isCustom = newSelectedClass == "Custom"
        if (!isCustom) {
            onDefaultCharacterSelected(newSelectedClass)

        } else {
            // if creating a custom character, clear out the fields
            resetCharacter()

        }
        _uiState.update { currentState ->
            currentState.copy(
                selectedClass = newSelectedClass, isCustom = isCustom, isDefault = !isCustom
            )
        }
    }

    fun resetCharacter() {
        _uiState.value = CharacterCreatorUiState()
    }
}