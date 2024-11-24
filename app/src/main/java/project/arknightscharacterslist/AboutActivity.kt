package project.arknightscharacterslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.arknightscharacterslist.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivFotodiri.setImageResource(R.drawable.foto_diri)
    }
}