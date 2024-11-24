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
            binding.tvCharName.text = char.name
            binding.tvCharProfile.text = char.descriptionProfile
            binding.tvCharStory.text = char.descriptionStory
            binding.tvCharOverview.text = char.descriptionOverview
            // Char Background, faction, & art
            Glide.with(binding.ivCharBg.context)
                .load(getString(R.string.data_char_background))
                .into(binding.ivCharBg)
            Glide.with(binding.ivCharFaction.context)
                .load(char.photoCharFaction)
                .into(binding.ivCharFaction)
            Glide.with(binding.ivCharArt.context)
                .load(char.photoCharElite1)
                .into(binding.ivCharArt)
            // Char Class & Subclass
            Glide.with(binding.ivCharClass.context)
                .load(char.photoClass)
                .into(binding.ivCharClass)
            Glide.with(binding.ivCharSubclass.context)
                .load(char.photoSubclass)
                .into(binding.ivCharSubclass)
            // imageButton
            Glide.with(binding.ibCharElite1.context)
                .load(getString(R.string.data_char_elite_1_logo))
                .into(binding.ibCharElite1)
            Glide.with(binding.ibCharElite2.context)
                .load(getString(R.string.data_char_elite_2_logo))
                .into(binding.ibCharElite2)
            binding.ibCharElite1.setOnClickListener {
                Glide.with(binding.ivCharArt.context)
                    .load(char.photoCharElite1)
                    .into(binding.ivCharArt)
            }
            binding.ibCharElite2.setOnClickListener {
                Glide.with(binding.ivCharArt.context)
                    .load(char.photoCharElite2)
                    .into(binding.ivCharArt)
            }
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