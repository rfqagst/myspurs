package com.example.myspurssubmission

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.myspurssubmission.databinding.DetailItemBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }
    private lateinit var detailBinding: DetailItemBinding
    private var player: Player? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DetailItemBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        player = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Player>(coba.EXTRA_PLAYER, Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>(coba.EXTRA_PLAYER)
        }

        player?.also { p ->
            detailBinding.apply {
                tvName.text = p.name
                tvPositionValue.text = p.position
                imgProfile.setImageResource(p.photo)
                tvNationalityValue.text = p.nationality
                tvGoalsValue.text = p.goals.toString()
                tvDesciption.text = p.description
                tvBornValue.text = p.born
                tvHeightWeightValue.text = p.heightWeight.toString()
                tvAppearancesValue.text = p.appearance.toString()
                tvWinsValue.text = p.wins.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun shareContent() {
        val articleContent = player?.let {
            """
            Name: ${it.name}
            Position: ${it.position}
            Nationality: ${it.nationality}
            Goals: ${it.goals}
            Description: ${it.description}
            Born: ${it.born}
            Height/Weight: ${it.heightWeight}
            Appearances: ${it.appearance}
            Wins: ${it.wins}
            """.trimIndent()
        } ?: "No player details available"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, articleContent)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Player Overview"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share_menu -> {
                shareContent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
