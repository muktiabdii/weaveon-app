package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.R
import com.example.hology.ui.theme.Secondary08
import com.example.hology.ui.theme.Secondary09

@Composable
fun TicketCard(
    number: String,
    title: String,
    description: String,
    isDone: Boolean = false,
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier // kasih default biar nggak error kalau lupa isi
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        // ticket background
        Image(
            painter = painterResource(id = R.drawable.exercise_ticket),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // number circle / done icon
            Box(
                modifier = Modifier.size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isDone) {
                    Image(
                        painter = painterResource(id = R.drawable.exercise_done),
                        contentDescription = "Done",
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Text(
                        text = number,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Secondary09,
                            fontSize = 27.sp,
                        )
                    )
                }
            }

            // description
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Secondary09,
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Secondary08,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
