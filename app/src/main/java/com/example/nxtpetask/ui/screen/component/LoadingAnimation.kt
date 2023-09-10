package com.example.nxtpetask.ui.screen.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotsLoadingAnimation(
    modifier: Modifier = Modifier,
    dotColor: Color,
    dotSize: Int = 10
) {
    val dotSpacing = (dotSize * 5.6).dp
    val dotRadius = (dotSize.dp / 2).toPx()
    val durationInMillis = 1400
    val (initialSize, finalSize) = 1f to 1.8f

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = initialSize,
        targetValue = finalSize,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationInMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse,
//            initialStartOffset = StartOffset(500)
        ),
        label = ""
    )

    val scale2 by infiniteTransition.animateFloat(
        initialValue = initialSize,
        targetValue = finalSize,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationInMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(700)
        ),
        label = ""
    )

    val scale3 by infiniteTransition.animateFloat(
        initialValue = initialSize,
        targetValue = finalSize,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationInMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(1100)
        ),
        label = ""
    )

    Canvas(
        modifier = modifier.wrapContentSize(),
        onDraw = {
            val centerX = size.width / 2
            val centerY = size.height / 2

            drawCircle(
                color = dotColor,
                radius = dotRadius * scale,
                center = Offset(centerX - dotSpacing.value, centerY)
            )

            drawCircle(
                color = dotColor,
                radius = dotRadius * scale2,
                center = Offset(centerX, centerY)
            )

            drawCircle(
                color = dotColor,
                radius = dotRadius * scale3,
                center = Offset(centerX + dotSpacing.value, centerY)
            )
        }
    )
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return (this@toPx * density).value
}

