package com.example.pizzaapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaapp.models.Order


class MainActivity : AppCompatActivity() {
    lateinit var txtLastName: EditText
    lateinit var txtFirstname: EditText
    lateinit var txtAddress: EditText
    lateinit var sizeSpinner: Spinner
    lateinit var txtIngredients: EditText
    lateinit var orderBtn: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtLastName = findViewById(R.id.editTextLastName)
        txtFirstname = findViewById(R.id.editTextFirstName)
        txtAddress = findViewById(R.id.editTextAddress)
        sizeSpinner = findViewById(R.id.pizzaSizeSpinner)
        txtIngredients = findViewById(R.id.editTextIngredients)
        orderBtn = findViewById(R.id.orderButton)

        val pizzaSizes = listOf<String>("S", "M", "L")

        sizeSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzaSizes)

        orderBtn.setOnClickListener {
            var order = Order(
                txtFirstname.text.toString(),
                txtLastName.text.toString(),
                txtAddress.text.toString(),
                sizeSpinner.selectedItem.toString(),
                txtIngredients.text.toString()
            )

            if (order.isValid()) {
                sendEmail(arrayOf("alabenhmouda@gmail.com"), order.getTitle(), order.toString())
            } else {
                toastMessage("Order is invalid, please check the fields!")
            }
        }
    }

    fun sendEmail(recipients: Array<String>, subject: String, body: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"

        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, body)

        try {
            startActivity(Intent.createChooser(emailIntent, "Select an email app"))
        } catch (exception: ActivityNotFoundException) {
            toastMessage("There is no email client installed")
        }
    }

    fun toastMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
            .show()
    }

}