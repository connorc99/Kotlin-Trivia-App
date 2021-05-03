import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R

class MultipleChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)
        supportActionBar?.hide()
//        val i = Intent(this, TrueFalse::class.java)
//        startActivity(i)
    }
}