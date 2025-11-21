package com.example.charactercreator.model
/**
 * ----------------------------------------------------------------------------
 * Project 4 Starter Code – ViewModel (CharacterCreatorViewModel)
 * ----------------------------------------------------------------------------
 *
 * This ViewModel stores ALL character data and is responsible for:
 *
 *   • Updating name, class, and description
 *   • Updating individual stats (+ / -)
 *   • Updating all stats at once (optional helper)
 *   • Computing attributes (attack, defense, cost) automatically
 *   • Resetting the character when needed
 *   • Loading a default character when the user selects a premade class
 *
 * WHAT THIS FILE PROVIDES:
 *   ✓ A MutableStateFlow holding the current UI state
 *   ✓ Helper fields:
 *         areTextFieldsFilled
 *         areAllPointsSpent
 *   ✓ Method signatures for all required logic
 *
 * WHAT YOU MUST IMPLEMENT:
 *   • updateStat() using Character.withUpdatedStat()
 *   • updateStats() (optional helper)
 *   • onNameChange(), onClassChange(), onDescriptionChange()
 *   • onDefaultCharacterSelected() to load premade characters
 *   • onSelectedClassChange() to switch between default and custom modes
 *   • resetCharacter() to clear the state
 *
 * IMPORTANT NOTES:
 *   - NEVER mutate Character directly. Always use `copy()` to produce a new one.
 *   - ALWAYS update state using `_uiState.update { … }`
 *   - The UI re-renders automatically whenever uiState changes.
 *
 * ----------------------------------------------------------------------------
 */

import androidx.lifecycle.ViewModel
import com.example.charactercreator.data.DataSource
import com.example.charactercreator.data.DataSource.defaultCharacters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CharacterCreatorViewModel : ViewModel() {
    /**
     * The ViewModel holds a StateFlow of CharacterCreatorUiState.
     *
     * UI screens will collect this state:
     *      val uiState by viewModel.uiState.collectAsState()
     *
     * and automatically recompose whenever the state updates.
     */
    private var _uiState = MutableStateFlow(CharacterCreatorUiState())
    val uiState = _uiState.asStateFlow()


    /**
     * Helper property:
     * Returns true if *all* required text fields (name, class, description)
     * have non-empty values.
     *
     * You can use this to enable/disable the Next button on Screen 1.
     */
    val areTextFieldsFilled: Boolean
        get() = !_uiState.value.currentCharacter.name.isEmpty() &&
                !_uiState.value.currentCharacter.charClass.isEmpty() &&
                !_uiState.value.currentCharacter.description.isEmpty()

    /**
     * Helper property:
     * Returns true only when the user has spent every available stat point.
     *
     * You can use this to enable/disable the Next button on the Talent screen.
     */
    val areAllPointsSpent: Boolean
        get() = _uiState.value.currentCharacter.maxPoints == _uiState.value.currentCharacter.totalPoints

    /**
     * updateStat()
     * ------------------------------------------------------------------------
     * Called when the user taps + or – on a stat.
     *
     * Parameters:
     *   statName : String    → which stat to update ("Power", "Speed", etc.)
     *   delta    : Int       → +1 or -1
     *   maxPoints: Int       → upper limit for total stat points
     *
     * TODO:
     *   - Grab the current character from uiState
     *   - Call character.withUpdatedStat(statName, delta, maxPoints)
     *   - Use _uiState.update { ... } to store the new character
     *
     * NOTE:
     *   withUpdatedStat() returns the SAME character if no change is allowed
     *   (e.g., would exceed maxPoints or go below 0). Only update the state
     *   when a change actually happens.
     * ------------------------------------------------------------------------
     */
    fun updateStat(
        statName: String, delta: Int, maxPoints: Int
    ) {
        // TODO
    }

    /**
     * updateStats()
     * ------------------------------------------------------------------------
     * Optional helper methods to update all stats at once via a map or array.
     *
     * These are useful if you want to load a default character template.
     *
     * TODO: Implement the map version at minimum, updating:
     *     - statMap
     *     - attribMap (use Character.computeAttributes())
     */
    fun updateStats(
        newStatMap: Map<String, Int>
    ) {
        // TODO
    }

    /**
     * onNameChange()
     * ------------------------------------------------------------------------
     * Called whenever the name TextField updates.
     *
     * TODO:
     *   - Use _uiState.update { currentState -> ... }
     *   - Replace currentCharacter with a copy containing the new name.
     *
     * Example pattern:
     *   _uiState.update { state ->
     *       state.copy(currentCharacter = state.currentCharacter.copy(name = newName))
     *   }
     */
    fun onNameChange(newName: String) {
        // TODO
    }

    /**
     * onClassChange()
     * ------------------------------------------------------------------------
     * Called when the user TYPES a class name (custom class mode).
     * This should NOT load default character data. It should only change
     * the charClass field in the Character.
     */
    fun onClassChange(newClass: String) {
        // TODO
    }

    /**
     * onDescriptionChange()
     * ------------------------------------------------------------------------
     * TODO:
     *   - Update the character's description field
     *   - Follow the same pattern used in onNameChange()
     */
    fun onDescriptionChange(newDescription: String) {
        // TDOO
    }


    /**
     * onDefaultCharacterSelected()
     * ------------------------------------------------------------------------
     * Called when the user selects a premade class from the dropdown.
     *
     * TODO:
     *   - Find the Character in defaultCharacters that matches defaultClass
     *   - Set uiState.currentCharacter to that Character
     *
     * DataSource.defaultCharacters is a List<Character> you can search through.
     */
    fun onDefaultCharacterSelected(defaultClass: String) {
        // TODO
    }

    /**
     * onSelectedClassChange()
     * ------------------------------------------------------------------------
     * Called whenever the class dropdown selection changes.
     *
     * newSelectedClass can be:
     *   • One of the default class names → load default character
     *   • "Custom" → reset to a blank character and allow typing
     *
     * TODO:
     *   - Check if newSelectedClass is "Custom"
     *       → call resetCharacter(), then allow typing
     *   - Otherwise:
     *       → call onDefaultCharacterSelected(newSelectedClass)
     *
     * Also update any uiState flags you need (isCustom, isDefault, etc.).
     * ------------------------------------------------------------------------
     */
    fun onSelectedClassChange(newSelectedClass: String) {
        // TODO
    }

    /**
     * resetCharacter()
     * ------------------------------------------------------------------------
     * TODO:
     *   - Reset the UI state back to a brand-new CharacterCreatorUiState()
     *   - This clears ALL fields (name, class, description, stats, etc.)
     *
     * This is used both by the Reset button on Screen 1
     * and the Cancel button on Screen 3.
     * ------------------------------------------------------------------------
     */
    fun resetCharacter() {
        // TODO
    }
}