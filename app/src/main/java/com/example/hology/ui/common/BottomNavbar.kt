package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hology.R
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary10

@Composable
fun BottomNavBar(
    navController: NavController,
    selected: String
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x33000000),
                ambientColor = Color(0x33000000)
            )
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(color = Color(0xFFFFBFA9))
            .padding(top = 13.dp, bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // nav item
            NavItem(
                icon = R.drawable.ic_home,
                label = "home",
                isSelected = selected == "home",
                onClick = {
                    if (selected != "home") {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = R.drawable.ic_report,
                label = "report",
                isSelected = selected == "report",
                onClick = {
                    if (selected != "report") {
                        navController.navigate("report") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = R.drawable.ic_user,
                label = "profile",
                isSelected = selected == "profile",
                onClick = {
                    if (selected != "profile") {
                        navController.navigate("profile") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor = Primary10
    val inactiveColor = NeutralWhite
    val tintColor = if (isSelected) activeColor else inactiveColor

    Column(
        modifier = modifier
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(32.dp),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(tintColor)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = tintColor,
            textAlign = TextAlign.Center
        )
    }
}
