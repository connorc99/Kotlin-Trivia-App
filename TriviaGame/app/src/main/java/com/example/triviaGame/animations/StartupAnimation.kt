package com.example.triviaGame.animations

import RegistrationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R
import kotlinx.android.synthetic.main.activity_startup_animation.*

class StartupAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup_animation)
        triviaStart.alpha = 0f
        triviaStart.animate().setDuration(1000).alpha(1f).withEndAction{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
            finish()
        }
    }
}