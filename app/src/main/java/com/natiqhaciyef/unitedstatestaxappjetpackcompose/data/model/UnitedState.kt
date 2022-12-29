package com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.model

data class UnitedState(
    val id: Int,
    val state: String,
    val stateCode: String,
    val taxPercent: Double,
    val incomeTax: Double,
    val minWage: Double,
    val image: Int
)
