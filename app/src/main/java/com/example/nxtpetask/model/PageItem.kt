package com.example.nxtpetask.model

data class PageItem(
    val customer_linked_account: CustomerLinkedAccount,
    val mandate_details: MandateDetails,
    val paymentoptions: List<Paymentoption>,
    val title: String,
    val type: String
)