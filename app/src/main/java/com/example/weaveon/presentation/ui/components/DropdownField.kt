package com.example.weaveon.presentation.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Primary07

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    placeholder: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    leadingIcon: Int,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // Animasi rotasi, 0f saat collapsed, 180f saat expanded
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 13.sp,
                lineHeight = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Primary07,
                unfocusedPlaceholderColor = Primary07,
                unfocusedTextColor = Primary07,
                focusedBorderColor = Primary07,
                unfocusedBorderColor = Primary07,
                containerColor = Color.White
            ),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.graphicsLayer {
                        rotationZ = rotation
                    }
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 13.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DropdownFieldPreview() {
    val ageOptions = listOf("2-3 tahun", "Opsi 1", "Opsi 2", "Opsi 3")
    var selectedAgeOption by remember { mutableStateOf("") }

    DropdownField(
        placeholder = "Input Placeholder",
        options = ageOptions,
        selectedOption = selectedAgeOption,
        onOptionSelected = { selectedAgeOption = it },
        leadingIcon = R.drawable.ic_doll
    )
}
