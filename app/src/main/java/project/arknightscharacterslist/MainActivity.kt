package project.arknightscharacterslist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.arknightscharacterslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvChars: RecyclerView
    private var list = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvChars = findViewById(R.id.rv_chars)
        rvChars.setHasFixedSize(true)

        list.addAll(getListChars())
        showRecyclerList()

        binding.aboutPage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.aboutPage.id -> {
                val moveToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveToAbout)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvChars.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvChars.layoutManager = GridLayoutManager(this,2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListChars(): ArrayList<Char> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataCharProfile = resources.getStringArray(R.array.data_char_profile)
        val dataCharIcon = resources.getStringArray(R.array.data_char_icon_photo)
        val dataCharClassPhoto = resources.getStringArray(R.array.data_char_class_photo)
        val dataCharSubclassPhoto = resources.getStringArray(R.array.data_char_subclass_photo)
        val dataCharStoryText = resources.getStringArray(R.array.data_char_story)
        val dataCharOverviewText = resources.getStringArray(R.array.data_char_overview)
        val dataCharFactionPhoto = resources.getStringArray(R.array.data_char_faction_photo)
        val dataCharElite1Photo = resources.getStringArray(R.array.data_char_elite_1_photo)
        val dataCharElite2Photo = resources.getStringArray(R.array.data_char_elite_2_photo)
        val dataCharUrl = resources.getStringArray(R.array.data_char_url)
        val listChar = ArrayList<Char>()
        for (i in dataName.indices) {
            val char = Char(
                dataName[i],
                dataCharProfile[i],
                dataCharIcon[i],
                dataCharClassPhoto[i],
                dataCharSubclassPhoto[i],
                dataCharStoryText[i],
                dataCharOverviewText[i],
                dataCharFactionPhoto[i],
                dataCharElite1Photo[i],
                dataCharElite2Photo[i],
                dataCharUrl[i]
                )
            listChar.add(char)
        }
        return listChar
    }

    private fun showRecyclerList() {
        rvChars.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharAdapter(list)
        rvChars.adapter = listCharAdapter

        listCharAdapter.setOnItemClickCallback(object : ListCharAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Char) {
                showSelectedChar(data)
            }
        })
    }

    private fun showSelectedChar(char: Char) {
        val moveToCharDetail = Intent(this@MainActivity, CharDetailActivity::class.java)
        moveToCharDetail.putExtra(CharDetailActivity.DETAIL_CHAR, char)
        startActivity(moveToCharDetail)
    }
}