package com.example.nxtpetask.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nxtpetask.ui.screen.component.MinimalDialog
import com.example.nxtpetask.ui.screen.component.PayUsingCard
import com.example.nxtpetask.ui.screen.component.PaymentOptionCard
import com.example.nxtpetask.ui.theme.Black
import com.example.nxtpetask.ui.theme.DarkOrange
import com.example.nxtpetask.ui.theme.LightOrange
import com.example.nxtpetask.ui.theme.OpenSans
import com.example.nxtpetask.ui.theme.Orange
import com.example.nxtpetask.ui.theme.SeparatorColor
import com.example.nxtpetask.ui.theme.TextColorGrey
import com.example.nxtpetask.ui.viewmodel.HomeViewModel
import com.example.nxtpetask.util.ApiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), exitApp: () -> Unit) {
    var selectedFSPIdx by remember { mutableStateOf(-1) }

    when (viewModel.mandateState.value) {
        is ApiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = Orange,
                    modifier = Modifier.size(56.dp)
                )
            }
        }

        is ApiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                "Some Error has occurred\nPlease try again!",
                Toast.LENGTH_LONG
            ).show()
            Log.d("retro", viewModel.mandateState.value.errorMsg!!)
        }

        is ApiState.Success -> {
            val data = viewModel.mandateState.value.data!!

            Column(Modifier.fillMaxSize()) {
                Surface(shadowElevation = 6.dp, modifier = Modifier.padding(bottom = 16.dp)) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                data.page_title,
                                fontSize = 16.sp,
                                color = Black,
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                tint = Orange,
                                contentDescription = "Back Button",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .size(34.dp)
                                    .clickable { exitApp() }
                            )
                        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White)
                    )
                }

                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 16.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(2.dp)
                ) {
                    val mandateDetails = data.page_items[0].mandateDetails!!

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(PaddingValues(16.dp, 16.dp, 0.dp, 16.dp))
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Details(
                                subHeading = mandateDetails.details[0].key,
                                value = mandateDetails.details[0].value,
                                fontSize = 13.sp
                            )

                            Details(
                                subHeading = mandateDetails.details[2].key,
                                value = mandateDetails.details[2].value,
                                fontSize = 11.5.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .height((0.5).dp)
                                .background(SeparatorColor)
                        )

                        Details(
                            subHeading = mandateDetails.details[1].key,
                            value = mandateDetails.details[1].value,
                            fontSize = 13.sp
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .height((0.5).dp)
                                .background(SeparatorColor)
                        )

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp)
                                .background(LightOrange, RoundedCornerShape(9.dp))
                                .padding(8.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null,
                                tint = DarkOrange,
                                modifier = Modifier
                                    .padding(end = 4.dp, top = 3.dp)
                                    .size(18.dp)
                            )

                            Text(
                                buildAnnotatedString {
                                    append(mandateDetails.message + "\nThe Limit is upto ")
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(mandateDetails.details[2].value)
                                    }
                                },
                                color = TextColorGrey,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                modifier = Modifier
                            )
                        }

                    }
                }

                val paymentOptions = data.page_items[1]

                Text(
                    paymentOptions.title!!,
                    fontSize = 14.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 14.dp)
                        .align(CenterHorizontally),
                ) {
                    items(paymentOptions.paymentOptions!!.size) {
                        PaymentOptionCard(
                            imageUrl = paymentOptions.paymentOptions[it].icon,
                            selected = selectedFSPIdx == it,
                            onSelect = {
                                selectedFSPIdx = it
                            }
                        )
                    }

                }

                val payUsingOptions = data.page_items[2]

                Text(
                    payUsingOptions.title!!,
                    fontSize = 13.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    items(payUsingOptions.customerLinkedAccount.options.size) {
                        PayUsingCard(
                            text = payUsingOptions.customerLinkedAccount.options[it].text,
                            iconUrl = payUsingOptions.customerLinkedAccount.options[it].icon
                        ) {
                            viewModel.showLoadingDialog = true
                        }
                    }
                }

                if (viewModel.showLoadingDialog)
                    MinimalDialog(
                        data.status_check.approve_request_txt,
                        seconds = viewModel.timer.value
                    ) {
                        viewModel.countDownTimer.start()
                    }
                else if (viewModel.showConfirmationDialog)
                    MinimalDialog(
                        data.status_check.approve_success_txt
                    )

            }

        }
    }
}

@Composable
fun Details(subHeading: String, value: String, fontSize: TextUnit) {
    Text(
        buildAnnotatedString {
            append("$subHeading - ")
            withStyle(
                SpanStyle(
                    fontWeight = if (value.substring(0, 3) == "UGX") FontWeight.Bold
                    else FontWeight.SemiBold
                )
            ) {
                append(value)
            }
        },
        fontSize = fontSize
    )
}
