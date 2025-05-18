package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.domain.model.ChatMessage
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Primary07
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.ui.theme.Secondary07

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
            if (message.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    color = Primary09,
                    strokeWidth = 2.dp
                )
            } else {
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
}