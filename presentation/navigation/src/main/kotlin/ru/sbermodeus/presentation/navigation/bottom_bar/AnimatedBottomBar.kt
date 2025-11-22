package ru.sbermodeus.presentation.navigation.bottom_bar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedBottomBar(
    selectedTabIndex: Int,
    animatedTabIndex: Float,
    animatedColor: Color,
    onTabSelected: (BottomBarTab, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .height(70.dp)
    ) {
        BarContent(
            tabs = tabs,
            selectedTab = selectedTabIndex,
            onTabSelected = { tab ->
                val idx = tabs.indexOf(tab)
                onTabSelected(tab, idx)
            }
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .07f),
                radius = size.height / 2 - 12,
                center = Offset(
                    (tabWidth * animatedTabIndex) + tabWidth / 2,
                    size.height / 2
                )
            )
        }
    }
}

@Composable
fun BarContent(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color(0xFF353535))
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onTabSelected(tab)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = "tab ${tab.title}",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    }
}