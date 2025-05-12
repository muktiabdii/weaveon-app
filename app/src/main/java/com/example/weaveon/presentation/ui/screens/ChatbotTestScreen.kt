package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weaveon.presentation.viewmodel.ChatbotViewModel

@Composable
fun ChatbotTestScreen(chatbotViewModel: ChatbotViewModel) {
    var prompt by remember { mutableStateOf("") }
    val reply by chatbotViewModel.reply.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Test Gemini Chatbot", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text("Your Prompt") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { chatbotViewModel.sendPrompt(prompt) }) {
            Text("Send to Gemini")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Gemini's Reply:")
        Text(text = reply)
    }
}
