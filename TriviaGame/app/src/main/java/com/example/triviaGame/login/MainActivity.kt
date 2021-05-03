package com.example.triviaGame.login
import com.example.triviaGame.entities.PlayerEntity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.triviaGame.R
import com.example.triviaGame.animations.MainScreenAnimation
import com.example.triviaGame.animations.StartupAnimation
import com.example.triviaGame.database.TriviaDatabase
import com.example.triviaGame.viewmodels.TriviaViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataSource = TriviaDatabase.getInstance(this).TriviaDao
        val TriviaViewModel = TriviaViewModel(dataSource, this.application)
        TriviaViewModel.removeCurrentUser()
        val login: Button = findViewById(R.id.loginButton)
        login.setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameTextView)
            val password = findViewById<EditText>(R.id.passwordTextView)
            if (username != null && password != null) {
                val account = PlayerEntity(
                    username.text.toString(),
                    password.text.toString()
                )
                if (TriviaViewModel.checkLogin(account)) {
                    TriviaViewModel.setCurrentUser(account)
                    val intent = Intent(this, MainScreenAnimation::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Enter the correct username and password to login.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Enter the correct username and password to login.", Toast.LENGTH_SHORT).show()
            }
        }

        val createAccount: Button = findViewById(R.id.CreateAccountButton)
        createAccount.setOnClickListener {
            val intent = Intent(this, StartupAnimation::class.java)
            startActivity(intent)
        }
    }
}
