package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary09

@Composable
fun SubmitButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(30.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Secondary05,
            contentColor = Secondary09,
        ), elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        )
    }
}

@Preview
@Composable
private fun PreviewSubmitButton() {
    SubmitButton(
        text = "Masuk",
        onClick = {},
        modifier = Modifier
    )
}
