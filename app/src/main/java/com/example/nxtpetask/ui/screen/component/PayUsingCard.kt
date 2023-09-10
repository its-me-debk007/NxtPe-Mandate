package com.example.nxtpetask.ui.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.nxtpetask.ui.theme.Black
import com.example.nxtpetask.ui.theme.DarkOrange
import com.example.nxtpetask.ui.theme.OpenSans

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PayUsingCard(text: String, iconUrl: String, onClick: () -> Unit) {

    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick() }
            .border(BorderStroke(0.3.dp, DarkOrange), RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(Color.White, contentColor = Black),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {

        Row(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = iconUrl,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                fontSize = 14.sp,
                fontFamily = OpenSans,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(2f)
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
            )
        }
    }
}