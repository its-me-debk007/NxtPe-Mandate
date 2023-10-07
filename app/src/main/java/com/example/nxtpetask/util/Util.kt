package com.example.nxtpetask.util

import androidx.compose.ui.unit.dp
import android.content.Context
import android.telephony.SubscriptionInfo
import android.util.Log

val DIALOG_CORNER_RADIUS = 6.dp

fun readSimCardsInfo(context: Context, activeSubscriptionInfoList: List<SubscriptionInfo>): List<String> {
    val simCardNames = mutableListOf<String>()

    activeSubscriptionInfoList.forEachIndexed { index, subscriptionInfo ->
        val carrierName = subscriptionInfo.carrierName.toString()
        simCardNames.add(carrierName)
        Log.d("duka", carrierName)
    }

    return simCardNames
}