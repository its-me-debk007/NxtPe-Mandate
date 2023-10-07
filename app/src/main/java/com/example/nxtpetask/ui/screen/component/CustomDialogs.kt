package com.example.nxtpetask.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.theme.ActiveTickGreen
import com.example.nxtpetask.ui.theme.InactiveTickGrey
import com.example.nxtpetask.ui.theme.Orange
import com.example.nxtpetask.util.DIALOG_CORNER_RADIUS
import kotlinx.coroutines.delay

@Composable
fun CustomDialog(simNo: Int, simName: String, onDismissRequest: () -> Unit, onProceed: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sim),
                    contentDescription = null
                )

                Text(
                    fontWeight = FontWeight.Medium,
                    text = "Confirm SIM",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        confirmButton = {
            Text(
                text = "Proceed",
                color = Orange,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { onProceed() })
        },
        dismissButton = {
            Text(text = "Change SIM", fontWeight = FontWeight.Normal, modifier = Modifier.padding(end = 18.dp).clickable { onDismissRequest() })
        },
        text = {
            Text(
                text = "You have selected SIM $simNo - $simName. Do you want to proceed?",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        },
        shape = RoundedCornerShape(DIALOG_CORNER_RADIUS),
        containerColor = Color.White
    )
}

@Composable
fun CustomVerificationDialog(navController: NavController, onDismissRequest: () -> Unit) {
    var stepsCompleted by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        delay(600)
        stepsCompleted = 0
        delay(1000)
        stepsCompleted++
        delay(1000)
        stepsCompleted++
        delay(200)
        navController.navigate("pin")
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(Modifier.background(Color.White, shape = RoundedCornerShape(DIALOG_CORNER_RADIUS)).padding(24.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.verify),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )

                Text(
                    fontWeight = FontWeight.Medium,
                    text = "Verify Mobile Number",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Step(text = "Verification Initiated", selected = stepsCompleted >= 0)

            Spacer(
                modifier = Modifier.offset(y = (-2).dp)
                    .padding(start = 11.dp)
                    .width(1.2.dp)
                    .height(16.dp)
                    .background(if (stepsCompleted >= 0) ActiveTickGreen else InactiveTickGrey)
            )

            Step(text = "SMS sent from your mobile", selected = stepsCompleted >= 1, modifier = Modifier.offset(y = (-4).dp))

            Spacer(
                modifier = Modifier.offset(y = (-6).dp)
                    .padding(start = 11.dp)
                    .width(1.2.dp)
                    .height(16.dp)
                    .background(if (stepsCompleted >= 1) ActiveTickGreen else InactiveTickGrey)
            )

            Step(text = "Verification Completed", selected = stepsCompleted >= 2, modifier = Modifier.offset(y = (-8).dp))
        }
    }
}

@Composable
fun Step(text: String, selected: Boolean, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
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

        Text(
            fontWeight = FontWeight.Medium,
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}