package com.example.myspurssubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvPlayer: RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPlayer = findViewById(R.id.rv_players)
        rvPlayer.setHasFixedSize(true)
        list.addAll(getListPlayer())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListPlayer(): ArrayList<Player> {
        val listPlayer = ArrayList<Player>()
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPosition = resources.getStringArray(R.array.data_position)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataGoals = resources.getIntArray(R.array.data_goals)
        val dataWins = resources.getIntArray(R.array.data_wins)
        val dataAppearances = resources.getIntArray(R.array.data_appearances)
        val dataBorn = resources.getStringArray(R.array.data_born)
        val dataHeightWeight = resources.getStringArray(R.array.data_height_weight)
        val dataNationality = resources.getStringArray(R.array.data_nationality)


        for (i in dataName.indices) {
            val player = Player(dataNationality[i],dataName[i],dataPosition[i], dataDescription[i], dataPhoto.getResourceId(i, -1),
                dataBorn[i],dataHeightWeight[i],dataAppearances[i],dataGoals[i],dataWins[i])
            listPlayer.add(player)
        }
        return listPlayer
    }
    private fun showRecyclerList() {
        rvPlayer.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvPlayer.adapter = listPlayerAdapter
    }
}