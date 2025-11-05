# 🎨 Color Selector – ViewModel and State Management

## Overview
In this assignment, you’ll take your **Color Selector** app and refactor it to use a **ViewModel** to manage state instead of using `remember { mutableStateOf(...) }` inside composables.

This project builds on what you’ve already learned about state and immutability in Jetpack Compose.  
By the end, your app will display the same behavior — selecting a color and adjusting sliders — but the state will now be **owned by a ViewModel**.

---

## 🧱 Starter Code
Your starting point is the **Color Selector app** from class. If you'd like to start from the project code in this directory you can do that also.

It currently:
- Uses `remember` and `mutableStateOf` to track the current color and name.  
- Has a `ColorChoice` data class with immutable fields.  
- Displays radio buttons, sliders, and a dropdown menu for selecting and adjusting colors.

You will **not** change the basic UI, but will move all color logic into a ViewModel.

---

## ⚙️ Setup – Add ViewModel Dependencies

In your `app/build.gradle.kts`, make sure the following are included:

```kotlin
dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")
}
```

Sync Gradle after adding them.

---

## 🧩 Part 1 – Create the ViewModel

Create a new file named `ColorViewModel.kt`.

Inside it:
1. Define a class `ColorViewModel` that extends `ViewModel`.  
2. Move your state into the ViewModel:
   ```kotlin
   class ColorViewModel : ViewModel() {
       private val _colorState = MutableStateFlow(ColorChoice("Red", Color.Red))
       val colorState: StateFlow<ColorChoice> = _colorState
   }
   ```
3. Add helper methods that update the color immutably:
   ```kotlin
   fun selectColor(name: String) {
       val newColor = colorFromString(name)
       _colorState.value = _colorState.value.copy(name = name, color = newColor)
   }

   fun updateChannel(channel: String, value: Float) {
       _colorState.value = _colorState.value.withUpdatedColor(channel, value)
   }
   ```

---

## 🎨 Part 2 – Connect the ViewModel to the UI

In `MainActivity.kt`, use the Compose `viewModel()` function:

```kotlin
val colorViewModel: ColorViewModel = viewModel()
val colorState by viewModel.colorState.collectAsState()\
val currentColor = colorState.value
```

Then pass the ViewModel’s data and methods to your composables:

```kotlin
ColorRadioButtons(
            currentColorStr = currentColor.name,
            onRadioButtonClick = { newColorStr ->
                colorViewModel.selectColor(newColorStr)
            }
        )
```

---

## 🧠 Part 3 – Update Your Composables

### `ColorSelectorApp`

Inside, remove the `remember { mutableStateOf(...) }` block.  
Use the parameters instead when calling your dropdown, radio buttons, and sliders.

---

### `ColorDropdownMenu`
Change the click callback to call `onSelectColor(colorOption)`.

### `ColorRadioButtons`
Replace your `colorState` logic with `onSelectColor`.

### `ColorSliders`
Replace:
```kotlin
updateColor = { colorName, newValue -> ... }
```
with:
```kotlin
updateColor = { colorName, newValue -> onUpdateChannel(colorName, newValue) }
```

---

## ✅ Part 4 – Test It

Run your app in the emulator or on a device.  
It should behave **exactly the same** as before, but all state now comes from the `ColorViewModel`.

You should be able to:
- Pick a color with radio buttons or dropdown.  
- Adjust red/green/blue sliders.  
- See the background update immediately.

---

## 💡 isFavorite (time permitting)
Add a field to the `ColorChoice` data class such as:
```kotlin
val isFavorite: Boolean = false
```
Then:
- Add a button that toggles it (`viewModel.toggleFavorite()`).
- Display whether the current color is a favorite.

---

## 🧾 Grading (10 pts)

| Task | Pts |
|------|-----:|
| Created `ColorViewModel` class | 2 |
| State moved from Composables to ViewModel | 2 |
| ViewModel exposes state via `StateFlow` or `mutableStateOf` | 1 |
| Composables use callbacks from ViewModel | 2 |
| App behavior matches original | 2 |
| Clean, working layout (no crashes) | 1 |

---

## 📸 Submission

Take a screenshot of your app running (with sliders or dropdown visible).  
Zip your entire Android Studio project and upload it to Dropbox.
