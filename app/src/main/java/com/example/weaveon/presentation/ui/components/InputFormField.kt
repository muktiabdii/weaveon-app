package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Primary07

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFormField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = 13.sp,
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
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(30.dp),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        textStyle = TextStyle(
            fontSize = 13.sp,
            fontFamily = FontFamily(
                Font(R.font.poppins_regular)
            )
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Primary07,
            unfocusedPlaceholderColor = Primary07,
            unfocusedTextColor = Primary07,
            focusedBorderColor = Primary07,
            unfocusedBorderColor = Primary07,
            containerColor = Color.White
        )
    )
}


@Preview
@Composable
private fun InputFormFieldPreview() {
    InputFormField(
        value = "",
        onValueChange = {},
        placeholder = "Masukkan Email Anda",
        leadingIcon = R.drawable.ic_email,
        modifier = Modifier
    )
}
