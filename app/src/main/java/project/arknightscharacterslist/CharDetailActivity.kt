package project.arknightscharacterslist

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.arknightscharacterslist.databinding.ActivityCharDetailBinding

class CharDetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_CHAR = "detail_char"
    }

    private lateinit var binding: ActivityCharDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val char = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Char>(DETAIL_CHAR, Char::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Char>(DETAIL_CHAR)
        }

        if (char != null) {
            val text = "Name: ${char.name.toString()}, \nDescription: ${char.description.toString()}"
            binding.tvTest.text = text
        }
    }
}