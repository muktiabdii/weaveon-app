package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.domain.model.ChatMessage
import com.example.weaveon.presentation.ui.components.ChatBubble
import com.example.weaveon.presentation.ui.theme.NeutralBlack
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary07
import com.example.weaveon.presentation.ui.theme.Secondary09
import com.example.weaveon.presentation.viewmodel.ChatbotViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(
    chatbotViewModel: ChatbotViewModel
) {
    val reply by chatbotViewModel.reply.collectAsState()
    val isLoading by chatbotViewModel.isLoading.collectAsState()
    val error by chatbotViewModel.error.collectAsState()
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var newMessage by remember { mutableStateOf("") }

    // Tangani loading dan respons
    LaunchedEffect(isLoading, reply, error) {
        if (isLoading) {
            messages.add(ChatMessage(content = "Loading...", isOutgoing = false, isLoading = true))
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        } else {
            if (messages.isNotEmpty() && messages.last().isLoading) {
                messages.removeAt(messages.size - 1) // Kompatibel dengan API 29
            }
            // Simpan reply ke variabel lokal untuk menghindari masalah smart cast
            val replyValue = reply
            if (replyValue != null) {
                messages.add(ChatMessage(content = replyValue, isOutgoing = false))
                coroutineScope.launch {
                    listState.animateScrollToItem(messages.size - 1)
                }
            }
            if (error != null) {
                messages.add(ChatMessage(content = "Error: $error", isOutgoing = false))
                coroutineScope.launch {
                    listState.animateScrollToItem(messages.size - 1)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9F3F2))
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
            // App Bar
            Surface(
                shadowElevation = 6.dp,
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFFEFEFE)
                    ),
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Aibu",
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Secondary09,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back navigation */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                                tint = Secondary09,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.koala_say_hi),
                            contentDescription = "Aibu mascot",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                    }
                )
            }

            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(messages) { message ->
                    ChatBubble(
                        message = message
                    )
                }
            }

            // Message input
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
                                messages.add(ChatMessage(content = newMessage, isOutgoing = true))
                                chatbotViewModel.sendPrompt(newMessage)
                                coroutineScope.launch {
                                    listState.animateScrollToItem(messages.size - 1)
                                }
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
