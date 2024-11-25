package project.arknightscharacterslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.arknightscharacterslist.databinding.ActivitySplashScreenBinding
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2000)
    }
}