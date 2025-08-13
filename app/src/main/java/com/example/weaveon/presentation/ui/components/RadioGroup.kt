package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.domain.model.Question
import com.example.weaveon.presentation.ui.theme.Primary07
import com.example.weaveon.presentation.ui.theme.Primary09

@Composable
fun RadioGroup(
    title: String,
    question: Question,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Primary09,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        question.options.forEach { option ->
            val isSelected = option == selectedOption

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary09,
                        unselectedColor = Primary07
                    )
                )
                Text(
                    text = option,
                    color = if (isSelected) Primary09 else Primary07,
                    fontFamily = if (isSelected) FontFamily(Font(R.font.poppins_medium)) else FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 13.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
