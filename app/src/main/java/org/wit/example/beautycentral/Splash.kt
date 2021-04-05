package org.wit.example.beautycentral

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.wit.example.beautycentral.main.MainActivity


class Splash : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        info("App has started")

        Handler().postDelayed({
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3_000L)
    }
}
