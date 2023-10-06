package com.example.nxtpetask.ui.screen

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log

fun readSimCardInfo(context: Context): String {
    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    val simState = when (telephonyManager.simState) {
        TelephonyManager.SIM_STATE_ABSENT -> "Absent"
        TelephonyManager.SIM_STATE_NETWORK_LOCKED -> "Network Locked"
        TelephonyManager.SIM_STATE_PIN_REQUIRED -> "PIN Required"
        TelephonyManager.SIM_STATE_PUK_REQUIRED -> "PUK Required"
        TelephonyManager.SIM_STATE_READY -> "Ready"
        TelephonyManager.SIM_STATE_UNKNOWN -> "Unknown"
        else -> "Unknown"
    }

    val operatorName = telephonyManager.networkOperatorName
    val countryCode = telephonyManager.simCountryIso
//    val phoneNumber = telephonyManager.line1Number ?: "N/A"

    Log.d("duka", operatorName)

    return operatorName
}