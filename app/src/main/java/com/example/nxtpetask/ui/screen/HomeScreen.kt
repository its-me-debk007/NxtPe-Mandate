package com.example.nxtpetask.ui.screen

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.theme.Black
import com.example.nxtpetask.ui.theme.DarkOrange
import com.example.nxtpetask.ui.theme.Grey
import com.example.nxtpetask.ui.theme.LightOrange
import com.example.nxtpetask.ui.theme.OpenSans
import com.example.nxtpetask.ui.theme.Orange
import com.example.nxtpetask.ui.theme.OrangeShade2
import com.example.nxtpetask.ui.theme.TextColorGrey
import com.example.nxtpetask.ui.viewmodel.HomeViewModel
import com.example.nxtpetask.util.ApiState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    var selectedFSPIdx by remember { mutableStateOf(-1) }

    when (viewModel.mandateState.value) {
        is ApiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ApiState.Error -> {
            Log.d("retro", viewModel.mandateState.value.errorMsg!!)
        }

        is ApiState.Success -> {
            val data = viewModel.mandateState.value.data!!

            Column(Modifier.fillMaxSize()) {
                Surface(shadowElevation = 3.dp) {
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
                                    .size(36.dp)
                                    .padding(start = 8.dp)
                                    .clickable {
                                        // TODO: Exit App
                                    }
                            )
                        },
//                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                    )
                }

                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Details(
                                subHeading = mandateDetails.details[0].key,
                                value = mandateDetails.details[0].value,
                                fontSize = 12.sp
                            )

                            Details(
                                subHeading = mandateDetails.details[2].key,
                                value = mandateDetails.details[2].value,
                                fontSize = 10.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .height((0.5).dp)
                                .background(Grey)
                        )

                        Details(
                            subHeading = mandateDetails.details[1].key,
                            value = mandateDetails.details[1].value,
                            fontSize = 12.sp
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .height((0.5).dp)
                                .background(Grey)
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
                                    .padding(end = 4.dp, top = 4.dp)
                                    .size(18.dp)
                            )

                            Text(
                                buildAnnotatedString {
                                    append(mandateDetails.message + "The Limit is upto ")
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(mandateDetails.details[2].value)
                                    }
                                },
                                color = TextColorGrey,
                                fontSize = 13.sp,
                                fontFamily = OpenSans,
                                lineHeight = 16.sp,
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
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
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
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
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
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append(value)
            }
        },
        fontFamily = OpenSans,
        fontSize = fontSize
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PaymentOptionCard(imageUrl: String, selected: Boolean = false, onSelect: () -> Unit) {
    val shape = RoundedCornerShape(10.dp)

    ElevatedCard(
        Modifier
            .size(120.dp)
            .padding(bottom = 8.dp, end = 16.dp)
            .border(
                BorderStroke(1.dp, if (selected) Orange else Color.Transparent),
                shape
            )
            .clickable { onSelect() },
        shape = shape,
        colors = CardDefaults.cardColors(if (selected) LightOrange else Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GlideImage(model = imageUrl, contentDescription = "FSP")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PayUsingCard(text: String, iconUrl: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick() }
            .border(BorderStroke(0.3.dp, DarkOrange), RoundedCornerShape(10.dp))
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
                .padding(start = 8.dp)
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
        )
    }
}

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
            delay(350)
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
                horizontalAlignment = CenterHorizontally,
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
                    fontFamily = OpenSans,
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