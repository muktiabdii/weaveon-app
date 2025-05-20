package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Secondary09

sealed class BottomBarItem(
    val title: String,
    val iconRes: Int,
    val selectedColor: Color = Secondary09,
    val unselectedColor: Color = Color(0xFFFEFEFE)
) {
    data object Home : BottomBarItem("Home", R.drawable.ic_home)
    data object Aibu : BottomBarItem("Aibu", R.drawable.ic_chatbot)
    data object Profile : BottomBarItem("Profile", R.drawable.ic_user)
}

@Composable
fun BottomBar(
    selectedItem: BottomBarItem,
    onItemSelected: (BottomBarItem) -> Unit
) {
    val items = listOf(
        BottomBarItem.Home,
        BottomBarItem.Aibu,
        BottomBarItem.Profile
    )

    NavigationBar(
        containerColor = Color(0xFFFFBFA9),
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        items.forEach { item ->
            val isSelected = item == selectedItem
            NavigationBarItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 2.dp),
                            tint = if (isSelected) item.selectedColor else item.unselectedColor
                        )
                        Text(
                            text = item.title,
                            color = if (isSelected) item.selectedColor else item.unselectedColor,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                },
                selected = isSelected,
                onClick = { onItemSelected(item) },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = item.selectedColor,
                    selectedTextColor = item.selectedColor,
                    unselectedIconColor = item.unselectedColor,
                    unselectedTextColor = item.unselectedColor,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE9F3F2)
@Composable
fun BottomBarPreview() {
    BottomBar(
        selectedItem = BottomBarItem.Aibu,
        onItemSelected = {}
    )
}