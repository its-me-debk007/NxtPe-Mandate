package com.example.nxtpetask.model

import com.squareup.moshi.Json

data class PageItem(
    @field:Json(name = "customer_linked_account")
    val customerLinkedAccount: CustomerLinkedAccount,
    @field:Json(name = "mandate_details")
    val mandateDetails: MandateDetails?,
    @field:Json(name = "paymentoptions")
    val paymentOptions: List<Paymentoption>?,
    val title: String?,
    val type: String
)