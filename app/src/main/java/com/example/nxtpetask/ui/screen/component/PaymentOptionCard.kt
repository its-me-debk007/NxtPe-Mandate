package com.example.nxtpetask.ui.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.nxtpetask.ui.theme.LightOrange
import com.example.nxtpetask.ui.theme.Orange

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PaymentOptionCard(imageUrl: String, selected: Boolean = false, onSelect: () -> Unit) {
    val shape = RoundedCornerShape(10.dp)

    ElevatedCard(
        Modifier
            .height(136.dp)
            .aspectRatio(1f)
            .padding(bottom = 8.dp, end = 16.dp)
            .border(
                BorderStroke(1.dp, if (selected) Orange else Color.Transparent),
                shape
            )
            .clickable { onSelect() },
        shape = shape,
        colors = CardDefaults.cardColors(if (selected) LightOrange else Color.White),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GlideImage(model = imageUrl, contentDescription = "FSP")
        }
    }
}