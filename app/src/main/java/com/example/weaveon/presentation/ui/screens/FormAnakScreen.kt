package com.example.weaveon.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.domain.model.QuestionType
import com.example.weaveon.domain.model.formQuestions
import com.example.weaveon.presentation.ui.components.ActionButton
import com.example.weaveon.presentation.ui.components.CheckboxGroup
import com.example.weaveon.presentation.ui.components.DropdownField
import com.example.weaveon.presentation.ui.components.InputFormField
import com.example.weaveon.presentation.ui.components.RadioGroup
import com.example.weaveon.presentation.ui.components.TopBar
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.ui.theme.Secondary09
import com.example.weaveon.presentation.viewmodel.KidscoverViewModel

@Composable
fun FormAnakScreen(
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    kidscoverViewModel: KidscoverViewModel,
) {

    // state data anak
    val childName by kidscoverViewModel.childName.collectAsState()
    val age by kidscoverViewModel.age.collectAsState()
    val gender by kidscoverViewModel.gender.collectAsState()
    val answers by kidscoverViewModel.answers.collectAsState()
    val isLoading by kidscoverViewModel.isLoading.collectAsState()
    val error by kidscoverViewModel.error.collectAsState()

    // umur dan gender
    val ageOptions = listOf("1-2 tahun", "3-4 tahun", "5-6 tahun", "7-8 tahun", "9-10 tahun")
    val genderOptions = listOf("Laki-laki", "Perempuan")

    Scaffold(
        topBar = {
            TopBar(
                title = "Kidscover",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NeutralWhite)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_4),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Form Anak",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Secondary09,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Input Nama Anak
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Nama Anak",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary09
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    InputFormField(
                        value = childName,
                        onValueChange = { kidscoverViewModel.setChildName(it) },
                        placeholder = "Ketik Nama Anak",
                        leadingIcon = R.drawable.ic_baby
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Dropdown Umur Anak
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Pilih Umur Anak",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary09
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    DropdownField(
                        placeholder = "Pilih umur anak",
                        options = ageOptions,
                        selectedOption = age,
                        onOptionSelected = { kidscoverViewModel.setAge(it) },
                        leadingIcon = R.drawable.ic_doll
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Dropdown Gender Anak
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Jenis Kelamin Anak",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary09
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    DropdownField(
                        placeholder = "Pilih jenis kelamin anak",
                        options = genderOptions,
                        selectedOption = gender,
                        onOptionSelected = { kidscoverViewModel.setGender(it) },
                        leadingIcon = if (gender == "Laki-laki") R.drawable.ic_boy else R.drawable.ic_girl
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Loop pertanyaan dari model formQuestions sesuai urutan id
                formQuestions.sortedBy { it.id }.forEach { question ->
                    Spacer(Modifier.height(20.dp))

                    when (question.type) {
                        is QuestionType.Radio -> {
                            // Ambil jawaban dari answers map, default ""
                            val selectedOption = answers[question.id]?.firstOrNull() ?: ""

                            RadioGroup(
                                title = question.title,
                                question = question,
                                selectedOption = selectedOption,
                                onOptionSelected = { selected ->
                                    // Update answers di ViewModel
                                    val updatedAnswers = answers.toMutableMap()
                                    updatedAnswers[question.id] = listOf(selected)
                                    kidscoverViewModel.setAnswers(updatedAnswers)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        is QuestionType.Checkbox -> {
                            // Ambil jawaban list, default emptyList
                            val selectedOptions = answers[question.id]?.toMutableList() ?: mutableListOf()

                            CheckboxGroup(
                                title = question.title,
                                question = question,
                                selectedOptions = selectedOptions,
                                onOptionSelected = { option, isSelected ->
                                    val updatedOptions = selectedOptions.toMutableList()
                                    if (isSelected) {
                                        if (!updatedOptions.contains(option)) updatedOptions.add(option)
                                    }

                                    else {
                                        updatedOptions.remove(option)
                                    }
                                    val updatedAnswers = answers.toMutableMap()
                                    updatedAnswers[question.id] = updatedOptions
                                    kidscoverViewModel.setAnswers(updatedAnswers)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                ActionButton(
                    text = "Selanjutnya",
                    isLoading = isLoading,
                    onClick = {
                        kidscoverViewModel.saveChildData { success ->
                            if (success) {
                                onNextClick()
                            }
                        }
                    }
                )
            }
        }
    }
}
