package com.example.weaveon.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.components.ChatBubble
import com.example.weaveon.presentation.ui.components.TopBar
import com.example.weaveon.presentation.ui.theme.NeutralBlack
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary07
import com.example.weaveon.presentation.viewmodel.ChatbotViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(
    chatbotViewModel: ChatbotViewModel,
    onBackClick: () -> Unit = {}
) {
    val chatHistory by chatbotViewModel.chatHistory.collectAsState()
    val isLoading by chatbotViewModel.isLoading.collectAsState()
    val error by chatbotViewModel.error.collectAsState()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var newMessage by remember { mutableStateOf("") }

    // Scroll to bottom setiap chatHistory berubah
    LaunchedEffect(chatHistory.size) {
        if (chatHistory.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(chatHistory.size - 1)
            }
        }
    }

    LaunchedEffect(Unit) {
        chatbotViewModel.loadSavedChats()
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Aibu",
                onBackClick = { onBackClick() },
                painterResource(id = R.drawable.koala_say_hi)
            )
        },
        containerColor = Color(0xFFE9F3F2)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Chat Messages
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(chatHistory) { message ->
                        ChatBubble(message = message)
                    }

                    // Jika loading, tampilkan loading bubble di bawah chat
                    if (isLoading) {
                        item {
                            ChatBubble(
                                message = com.example.weaveon.domain.model.ChatMessageDomain(
                                    content = "Loading...",
                                    isOutgoing = false,
                                    isLoading = true
                                )
                            )
                        }
                    }

                    // Jika error, tampilkan pesan error di bawah chat
                    if (error != null) {
                        item {
                            ChatBubble(
                                message = com.example.weaveon.domain.model.ChatMessageDomain(
                                    content = "Error: $error",
                                    isOutgoing = false
                                )
                            )
                        }
                    }
                }

                // Input Message
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 6.dp,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    color = Color(0xFFFEFEFE)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = newMessage,
                            onValueChange = { newMessage = it },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                                .border(1.dp, Secondary07, RoundedCornerShape(30.dp)),
                            placeholder = {
                                Text(
                                    text = "Ketik pesanmu...",
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Secondary05,
                                    fontSize = 13.sp
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = NeutralWhite,
                                unfocusedContainerColor = NeutralWhite,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                unfocusedTextColor = Secondary05,
                                focusedTextColor = NeutralBlack,
                            ),
                            shape = RoundedCornerShape(24.dp),
                            singleLine = true
                        )

                        IconButton(
                            onClick = {
                                if (newMessage.isNotEmpty()) {
                                    // Kirim pesan user ke ViewModel
                                    chatbotViewModel.sendPrompt(newMessage)
                                    newMessage = ""
                                }
                            },
                            modifier = Modifier.size(55.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_send),
                                contentDescription = "Send",
                                modifier = Modifier.size(53.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
