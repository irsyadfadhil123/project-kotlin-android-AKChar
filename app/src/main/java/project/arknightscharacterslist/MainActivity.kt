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
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataCharPhoto = resources.getStringArray(R.array.data_char_photo)
        val dataClassPhoto = resources.getStringArray(R.array.data_class_photo)
        val dataSubclassPhoto = resources.getStringArray(R.array.data_subclass_photo)
        val dataCharDetailPhoto = resources.getStringArray(R.array.data_chardetail_photo)
        val listChar = ArrayList<Char>()
        for (i in dataName.indices) {
            val char = Char(dataName[i], dataDescription[i], dataCharPhoto[i], dataClassPhoto[i], dataSubclassPhoto[i], dataCharDetailPhoto[i])
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