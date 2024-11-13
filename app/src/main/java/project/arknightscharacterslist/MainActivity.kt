package project.arknightscharacterslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvChars: RecyclerView
    private var list = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvChars = findViewById(R.id.rv_chars)
        rvChars.setHasFixedSize(true)

        list.addAll(getListChars())
        showRecyclerList()
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
        val listChar = ArrayList<Char>()
        for (i in dataName.indices) {
            val char = Char(dataName[i], dataDescription[i], dataCharPhoto[i], dataClassPhoto[i], dataSubclassPhoto[i])
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
        Toast.makeText(this, "Kamu memilih " + char.name, Toast.LENGTH_SHORT).show()
    }
}