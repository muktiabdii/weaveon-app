package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Base
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Primary07
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary07
import com.example.weaveon.presentation.ui.theme.Secondary09

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen() {
    // For testing different message lengths
    val messages = remember {
        mutableStateListOf(
            ChatMessage("Hi Bunda! ada yang bisa kami bantu?", false),
            ChatMessage("Hi aibu!", true),
            ChatMessage("Tadi pagi Raka tantrum lagi pas mau berangkat terapi, aku buru-buru, dan dia langsung ngamuk", true),
            ChatMessage("Saya mengerti, Bu. Perubahan mendadak bisa membuat anak merasa tidak nyaman, apalagi jika ia belum siap secara emosional.", false),
            ChatMessage("Aku jadi merasa bersalah. Takut ini salahku terus", true),
            ChatMessage("Ibu sudah berusaha dengan sebaik mungkin. Raka mungkin hanya butuh waktu lebih untuk beradaptasi. Cobalah beri waktu bermain sebentar sebelum berangkat. ❤", false),
            // Add short messages to demonstrate adaptive sizing
            ChatMessage("Ya", true),
            ChatMessage("Baik", false),
            ChatMessage("OK!", true),
            ChatMessage("Terima kasih", false)
        )
    }

    var newMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9F3F2))
    ){
        Image(
            painter = painterResource(id = R.drawable.bg_3),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // App Bar
            Surface(
                shadowElevation = 6.dp, // Tambahkan shadow di sini
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
                            .border(1.dp, Secondary07, RoundedCornerShape(30.dp)), // Border/stroke
                        placeholder = {
                            Text(
                                text = "Ketik pesanmu...",
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Secondary05,
                                fontSize = 13.sp
                            ) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = NeutralWhite,
                            unfocusedContainerColor = NeutralWhite,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedTextColor = Secondary05,
                            focusedTextColor = Secondary05,
                            ),
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true
                    )

                    IconButton(
                        onClick = {
                            if (newMessage.isNotEmpty()) {
                                messages.add(ChatMessage(newMessage, true))
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

data class ChatMessage(
    val content: String,
    val isOutgoing: Boolean
)

@Composable
fun ChatBubble(
    message: ChatMessage
) {
    val outgoingGradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFEFFD5), Color(0xFFFFD9CC)),
        start = Offset(-50f, -50f),
        end = Offset(600f, 600f)
    )

    val incomingColor = NeutralWhite

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (message.isOutgoing) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .shadow(6.dp, RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomStart = 14.dp))
                .background(
                    brush = if (message.isOutgoing) outgoingGradient else Brush.linearGradient(listOf(incomingColor, incomingColor)),
                    shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomStart = 14.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .widthIn(max = 240.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = message.content,
                color = Primary09,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.wrapContentWidth()
            )

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(if (message.isOutgoing) Alignment.End else Alignment.Start),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (message.isOutgoing) {
                    Row(
                        modifier = Modifier
                            .clickable { /* Handle edit click */ }
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Edit",
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "edit",
                            fontSize = 11.sp,
                            color = Secondary07,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    }
                }

                val copyTextColor = if (message.isOutgoing) Secondary07 else Primary07
                val copyImage = if (message.isOutgoing) R.drawable.ic_copy else R.drawable.ic_copy_2

                Row(
                    modifier = Modifier
                        .clickable { /* Handle copy click */ }
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(copyImage),
                        contentDescription = "Copy",
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "copy",
                        fontSize = 11.sp,
                        color = copyTextColor,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatbotScreenPreview() {
    ChatbotScreen()
}