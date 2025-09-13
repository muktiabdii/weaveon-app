package com.example.hology.ui.report

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.R
import com.example.hology.domain.model.ChartData
import com.example.hology.ui.common.BarChart
import com.example.hology.ui.common.RecommendedActivityCard
import com.example.hology.ui.common.TicketCard
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Secondary03
import com.example.hology.ui.theme.Secondary09

data class TicketItem(
    val number: String,
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val category: String
)

@Composable
fun ReportScreen() {
    var selectedTab by remember { mutableStateOf("Grafik") }

    val chartData = remember {
        listOf(
            ChartData("Logika & Pola", 25f),
            ChartData("Seni & Visual", 50f),
            ChartData("Verbal", 50f),
            ChartData("Sosial & Imajinasi", 25f),
            ChartData("Musik & Auditori", 85f),
            ChartData("Logika & Pola", 75f)
        )
    }

    val ticketItems = remember {
        listOf(
            TicketItem(
                number = "A1",
                title = "Maze Sederhana",
                description = "",
                isDone = false,
                category = "Logika & Pola"
            ),
            TicketItem(
                number = "A1",
                title = "Menceritakan Cerita Bergambar",
                description = "",
                isDone = false,
                category = "Verbal"
            ),
            TicketItem(
                number = "A2",
                title = "Permainan \"Tebak Kata\"",
                description = "",
                isDone = false,
                category = "Verbal"
            ),
            TicketItem(
                number = "A3",
                title = "Word Association",
                description = "",
                isDone = false,
                category = "Verbal"
            ),
            TicketItem(
                number = "A1",
                title = "Sound Recognition",
                description = "",
                isDone = false,
                category = "Musik & Auditori"
            ),
            TicketItem(
                number = "A2",
                title = "Bermain Alat Musik",
                description = "",
                isDone = false,
                category = "Musik & Auditori"
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NeutralWhite)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_5),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            // header
            item {
                Text(
                    text = "Report",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 24.sp,
                    color = Secondary09,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 47.dp, bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // tab section (toggle)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = NeutralWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TabButton(
                            text = "Grafik",
                            isSelected = selectedTab == "Grafik",
                            onClick = { selectedTab = "Grafik" },
                            modifier = Modifier.weight(1f)
                        )
                        TabButton(
                            text = "Recent",
                            isSelected = selectedTab == "Recent",
                            onClick = { selectedTab = "Recent" },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(41.dp))
            }

            // content based on section
            when (selectedTab) {
                "Grafik" -> {
                    item {

                        // chart
                        BarChart(
                            modifier = Modifier.fillMaxWidth(),
                            data = chartData
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(top = 16.dp)
                        ) {

                            // conclution description
                            Text(
                                text = "Deskripsi",
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontSize = 16.sp,
                                color = Color(0xFF474828)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                border = BorderStroke(1.dp, NeutralBlack)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp)
                                ) {
                                    Text(
                                        text = "Pada aktivitas Maze Sederhana, anak menunjukkan antusiasme ...",
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        color = NeutralBlack
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // category description
                            Text(
                                text = "Deskripsi",
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontSize = 16.sp,
                                color = Color(0xFF474828)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                border = BorderStroke(1.dp, NeutralBlack)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp)
                                ) {
                                    Text(
                                        text = "Pada aktivitas Maze Sederhana, anak menunjukkan antusiasme ...",
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontSize = 16.sp,
                                        lineHeight = 20.sp,
                                        color = NeutralBlack
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(top = 38.dp, bottom = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Kegiatan yang Direkomendasikan",
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontSize = 16.sp,
                                color = Color(0xFF474828)
                            )

                            repeat(5) { index ->
                                RecommendedActivityCard(
                                    title = "Aktivitas ${index + 1}",
                                    description = "Deskripsi aktivitas ${index + 1}",
                                    imageRes = R.drawable.bg_5
                                )
                            }
                        }
                    }
                }

                "Recent" -> {
                    val groupedTickets = ticketItems.groupBy { it.category }

                    groupedTickets.forEach { (category, tickets) ->
                        item {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    color = NeutralBlack,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }

                        items(tickets) { ticket ->
                            TicketCard(
                                number = ticket.number,
                                title = ticket.title,
                                description = ticket.description,
                                isDone = ticket.isDone,
                                onItemClick = { /* handle */ },
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                            )
                        }

                        item { Spacer(modifier = Modifier.height(24.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected) Secondary03 else Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = NeutralBlack,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold))
        )
    }
}