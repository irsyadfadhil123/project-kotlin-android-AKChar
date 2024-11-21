package project.arknightscharacterslist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.isHideOnContentScrollEnabled
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val char = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DETAIL_CHAR, Char::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DETAIL_CHAR)
        }

        if (char != null) {
            supportActionBar?.title= char.name
            val text = "Name: ${char.name}, \nDescription: ${char.description}"
            binding.tvTest.text = text
            Glide.with(binding.ivChar.context)
                .load(char.photoCharDetail)
                .into(binding.ivChar)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
                }
        }
}