package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.logo)

        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.inifite_rotate))

        Handler().postDelayed({
            val appIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(appIntent)
        }, 5 * 1000)
    }
}