package com.example.pizzaapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Order (
    var firstName: String,
    var lastName: String,
    var address: String,
    var size: String,
    var addedIngredients: String
        )
{
    private fun getRowString(key: String, value: String): String = "$key: $value\n"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun toString(): String {
        var orderStr = ""
        orderStr += getRowString("Order date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
        orderStr += getRowString("First name", firstName)
        orderStr += getRowString("Last name", lastName)
        orderStr += getRowString("Address", address)
        orderStr += getRowString("size", size)
        val addedIngredientsString = if (addedIngredients.isNotBlank()) {
            addedIngredients
        } else {
            "None"
        }
        orderStr += getRowString("Ingredients added", addedIngredientsString)

        return orderStr
    }

    fun getTitle() = "Order - $firstName $lastName"

    fun isValid(): Boolean = firstName.isNotBlank() && lastName.isNotBlank() && address.isNotBlank()
            && size.isNotBlank()

}