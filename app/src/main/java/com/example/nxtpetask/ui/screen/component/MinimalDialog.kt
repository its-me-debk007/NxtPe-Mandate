package com.example.nxtpetask.ui.screen.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.theme.Black
import com.example.nxtpetask.ui.theme.OrangeShade2
import kotlinx.coroutines.delay

@Composable
fun MinimalDialog(text: String, seconds: Long = -1L, startTimer: () -> Unit = {}) {
    var showIcon by remember { mutableStateOf(false) }

    val iconSize by animateDpAsState(
        targetValue = if (showIcon) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    LaunchedEffect(Unit) {
        if (seconds != -1L) startTimer()
        else {
            delay(450)
            showIcon = true
        }
    }

    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(Color.White, Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp, 24.dp, 16.dp, 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (seconds != -1L) Text(
                    text = String.format("00:%02d", seconds),
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = OrangeShade2,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                else
                    Image(
                        painter = painterResource(id = R.drawable.ic_complete),
                        contentDescription = null,
                        modifier = Modifier.size(iconSize)
                    )

                Text(
                    text = text,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp)
                )

                DotsLoadingAnimation(
                    modifier = Modifier.fillMaxSize(),
                    dotColor = OrangeShade2
                )
            }

        }
    }
}