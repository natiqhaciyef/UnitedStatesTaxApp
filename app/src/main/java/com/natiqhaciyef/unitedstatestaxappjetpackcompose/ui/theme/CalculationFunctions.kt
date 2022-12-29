package com.natiqhaciyef.unitedstatestaxappjetpackcompose.ui.theme

import com.natiqhaciyef.unitedstatestaxappjetpackcompose.R
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.items.StatesList
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.model.UnitedState

fun searchState(state: String): UnitedState {
    var stateItem: UnitedState? = null
    for (element in StatesList.statesList) {
        if (element.state.contains(state)) {
            stateItem = element
        }
    }
    return stateItem ?: UnitedState(
        id = 34,
        state = "New Dakota",
        stateCode = "ND",
        taxPercent = 6.96,
        incomeTax = 4.31,
        minWage = 7.25,
        image = R.drawable.north_dakota_flag
    )
}

fun formatCustomer(input: String): String =
    if (input.isNotEmpty()) "%.2f".format(input.toDouble()) else "0.00"

fun incomeTaxCalculator(mainInput: String, fee: Double): String = "${mainInput.toDouble() - ((mainInput.toDouble() * fee) / 100)}"