package com.example.nxtpetask.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.screen.component.SimListItem
import kotlinx.coroutines.delay

@Composable
fun NxtDukaHome(navController: NavController) {
    var simSelected by remember { mutableStateOf(-1) }
    val simList = remember { mutableStateListOf<String>() }
    var showVerificationDialog by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        simList.add(readSimCardInfo(context))
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.sim_card),
            contentDescription = "SIM Card",
            modifier = Modifier
                .padding(top = 30.dp)
                .zIndex(10f)
        )

        Card(
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .offset(y = (-64).dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp, top = 76.dp)
            ) {

                Text(text = "Verify Mobile Number", fontWeight = FontWeight.Medium)

                Text(
                    text = "We need to send an sms from your phone to verify and register Your mobile number on nxtduka.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }

        Text(
            text = "Please select SIM registered with your bank & Ensure your mobile data is enabled",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .offset(y = (-64).dp)
        )

        LazyColumn(modifier = Modifier.offset(y = (-48).dp)) {
            items(simList.size) {
                SimListItem(simNo = it + 1, simName = simList[it], selected = simSelected == it) {
                    simSelected = it
                    showConfirmationDialog = true
                    Log.d("duka", simSelected.toString())
                }
            }
        }

        if (showConfirmationDialog) CustomDialog(
            simNo = 1,
            simName = simList.last(),
            onDismissRequest = {
                showConfirmationDialog = false
            },
            onProceed = {
                showConfirmationDialog = false
                showVerificationDialog = true
            })

        if (showVerificationDialog) CustomVerificationDialog(navController, onDismissRequest = {
            showVerificationDialog = false
        },)

        Text(
            text = "By selecting a SIM I agree to the Terms and Conditions. Regular carrier charges may apply.",
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}