package ir.karami.weather.screen.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchTextInput (
  hasBackButton: MutableState<Boolean>,
  focusManager: FocusManager,
  onSearchButtonClick: (cityName: String) -> Unit
) {
  var searchValue by remember { mutableStateOf("") }
  val focusRequester = remember { FocusRequester() }

  val customTextSelectionColors = TextSelectionColors(
    handleColor = Color.White.copy(0.9f),
    backgroundColor = Color.White.copy(alpha = 0.4f)
  )

  CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
    OutlinedTextField(
      value = searchValue,
      onValueChange = {
        searchValue = it
      },
      placeholder = {
        Text(
          text = "Search City Name ...",
          color = Color.White.copy(alpha = 0.5f)
        )
      },
      shape = RoundedCornerShape(64.dp),
      leadingIcon = if (hasBackButton.value.not()) {
        {
          IconButton(
            onClick = {
              hasBackButton.value = hasBackButton.value.not()
              focusRequester.requestFocus()
            }
          ) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = "Search Icon",
              tint = Color.White
            )
          }
        }
      } else null,
      trailingIcon = if (hasBackButton.value) {
        {
          Box(modifier = Modifier.padding(end = 16.dp)) {
            IconButton(
              onClick = {
                onSearchButtonClick.invoke(searchValue)
                focusManager.clearFocus()
              },
            ) {
              Text(text = "Find")
            }
          }
        }
      } else null,
      modifier = Modifier
        .fillMaxSize()
        .onFocusChanged {
          hasBackButton.value = it.hasFocus
        }
        .focusRequester(focusRequester),
      textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
      singleLine = true,
      keyboardActions = KeyboardActions(
        onSearch = {
          onSearchButtonClick.invoke(searchValue)
          focusManager.clearFocus()
        }
      ),
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        autoCorrect = true,
        capitalization = KeyboardCapitalization.None,
        imeAction = ImeAction.Search
      ),
      colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.White,
        disabledLabelColor = Color.White.copy(alpha = 0.5f),
        backgroundColor = Color.White.copy(0.1f),
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White.copy(0.5f),
        cursorColor = Color.White,
        focusedLabelColor = Color.White,
        leadingIconColor = Color.White,
        trailingIconColor = Color.White
      )
    )
  }
}
