package com.vncoder.logindemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_main.*

class SplashMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_main)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },4000)

        text_dot_loader.postDelayed(Runnable { text_dot_loader.setNumberOfDots(5) }, 3000)
    }
}