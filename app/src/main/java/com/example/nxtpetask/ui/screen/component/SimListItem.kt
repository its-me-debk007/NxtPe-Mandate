package com.example.nxtpetask.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.theme.ActiveTickGreen
import com.example.nxtpetask.ui.theme.InactiveTickGrey

@Composable
fun SimListItem(simNo: Int, simName: String, selected: Boolean = false, onSelect: () -> Unit) {
    Card(
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {
                onSelect()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_sim), contentDescription = null)

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Medium,
                    text = buildAnnotatedString {
                        append("SIM $simNo \n")
                        withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Light)) {
                            append(simName)
                        }
                    },
                    lineHeight = 17.sp
                )
            }

            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (selected) ActiveTickGreen else InactiveTickGrey,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
        }
    }
}