package com.example.hology.ui.report

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.UserData
import com.example.hology.cache.reportCategoryList
import com.example.hology.cache.wevyList
import com.example.hology.ui.common.BarChart
import com.example.hology.ui.common.RecommendedActivityCard
import com.example.hology.ui.common.TicketCard
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary03
import com.example.hology.ui.theme.Secondary03
import com.example.hology.ui.theme.Secondary09

@Composable
fun ReportScreen(
    navController: NavController,
    viewModel: ReportViewModel
) {
    val graphicState by viewModel.graphicState.collectAsState()
    val historyState by viewModel.historyState.collectAsState()
    val user = UserData

    var selectedTab by remember { mutableStateOf("Grafik") }

    val reportText by viewModel.reportText.collectAsState()
    val reportCategory = reportCategoryList.find { it.id == reportText.categoryId }

    LaunchedEffect(Unit) {
        viewModel.getCategoryChartData(user.uid)
        viewModel.getWevyHistory(user.uid)
    }

    LaunchedEffect(graphicState) {
        if (graphicState is GraphicState.Success) {
            viewModel.generateReport(user.uid, reportCategoryList)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralWhite)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_5),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // Header
            item {
                Text(
                    text = "Report",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 24.sp,
                    color = Secondary09,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Tab toggle
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
                            text = "Terkini",
                            isSelected = selectedTab == "Terkini",
                            onClick = { selectedTab = "Terkini" },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Content
            if (selectedTab == "Grafik") {
                when (graphicState) {
                    is GraphicState.Loading -> item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Primary03, strokeWidth = 10.dp)
                        }
                    }

                    is GraphicState.Success -> {
                        val chartData = (graphicState as GraphicState.Success).data

                        if (chartData.isEmpty()) {
                            item {
                                Text(
                                    text = "Belum ada aktivitas",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    textAlign = TextAlign.Center,
                                    color = Color.Gray,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        else{
                            item {
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
                                    // conclusion description
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
                                                text = reportText.conclusion,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontSize = 14.sp,
                                                lineHeight = 20.sp,
                                                color = NeutralBlack
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // category description
                                    Text(
                                        text = "🎯 ${reportCategory?.title ?: ""}",
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
                                                text = reportCategory?.description ?: "",
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontSize = 14.sp,
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

                                    reportCategory?.recommendedActivity?.forEach { recommendedActivity ->
                                        RecommendedActivityCard(
                                            title = recommendedActivity.title,
                                            description = recommendedActivity.description,
                                            imageRes = recommendedActivity.image
                                        )
                                    }
                                }
                            }
                        }
                    }

                    is GraphicState.Error -> item {
                        Text(
                            text = (graphicState as GraphicState.Error).message,
                            color = Color.Red
                        )
                    }

                    GraphicState.Idle -> {}
                }
            }

            if (selectedTab == "Terkini") {
                when (historyState) {
                    is HistoryState.Loading -> item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Primary03, strokeWidth = 10.dp)
                        }
                    }

                    is HistoryState.Success -> {
                        val history = (historyState as HistoryState.Success).data

                        // group history by wevyId
                        val groupedHistory = history.groupBy { it.wevyId }

                        groupedHistory.forEach { (wevyId, activities) ->
                            val wevy = wevyList.find { it.id == wevyId }
                            if (wevy != null) {

                                // title category
                                item {
                                    Text(
                                        text = wevy.title,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        color = NeutralBlack,
                                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                                    )
                                }

                                // list activities
                                activities.forEach { historyItem ->
                                    val activity = wevy.activities.find { it.id == historyItem.activityId }
                                    if (activity != null) {
                                        item {
                                            TicketCard(
                                                number = activity.id,
                                                title = activity.title,
                                                description = activity.description,
                                                isDone = false,
                                                onItemClick = {
                                                    navController.navigate("wevy_result/${wevy.id}/${activity.id}")
                                                },
                                                modifier = Modifier
                                                    .padding(horizontal = 20.dp)
                                                    .padding(bottom = 12.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    is HistoryState.Error -> item {
                        Text(
                            text = (historyState as HistoryState.Error).message,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp
                        )
                    }

                    HistoryState.Idle -> {}
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
