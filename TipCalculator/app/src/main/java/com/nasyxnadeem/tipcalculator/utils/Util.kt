package com.nasyxnadeem.tipcalculator.utils

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if(totalBill > 1 && totalBill.toString().isNotEmpty())
        (totalBill * 100) / 100
    else 0.0
}

fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPercentage: Int
): Double {
    val bill = calculateTotalTip(totalBill, tipPercentage) + totalBill

    return (bill / splitBy)


}