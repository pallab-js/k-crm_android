package com.nexus.crm.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.nexus.crm.ui.theme.BorderDefault
import com.nexus.crm.ui.theme.SupabaseGreen
import com.nexus.crm.ui.theme.MidGray
import com.nexus.crm.ui.theme.OffWhite

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Search...",
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = MidGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MidGray
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = SupabaseGreen,
            unfocusedBorderColor = BorderDefault,
            focusedTextColor = OffWhite,
            unfocusedTextColor = OffWhite,
            cursorColor = SupabaseGreen,
            focusedContainerColor = BorderDefault.copy(alpha = 0.3f),
            unfocusedContainerColor = BorderDefault.copy(alpha = 0.3f)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardOptions(
            imeAction = ImeAction.Search
        ).let { keystroke ->
            KeyboardActions(onSearch = { focusManager.clearFocus() })
        }
    )
}