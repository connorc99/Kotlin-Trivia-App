package com.example.triviaGame.homePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R
import kotlinx.android.synthetic.main.activity_view_leaderboard.*
import kotlinx.android.synthetic.main.activity_view_leaderboard.btnReturn
import kotlinx.android.synthetic.main.activity_view_my_scores.*

class ViewLeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_leaderboard)

        btnReturn.setOnClickListener {
            finish()
        }
    }
}