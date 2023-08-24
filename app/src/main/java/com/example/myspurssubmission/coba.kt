package com.example.myspurssubmission


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.myspurssubmission.databinding.DetailItemBinding

class coba : AppCompatActivity() {
    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }

    private lateinit var binding: DetailItemBinding
    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Player>(EXTRA_PLAYER, Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>(EXTRA_PLAYER)
        }

        if (player != null) {
            binding.tvName.text = player!!.name
            binding.tvPositionValue.text = player!!.position
            binding.imgProfile.setImageResource(player!!.photo)
            binding.tvNationalityValue.text = player!!.nationality
            binding.tvGoalsValue.text = player!!.goals.toString()
            binding.tvDesciption.text = player!!.description
            binding.tvBornValue.text = player!!.born
            binding.tvHeightWeightValue.text = player!!.heightWeight.toString()
            binding.tvAppearancesValue.text = player!!.appearance.toString()
            binding.tvWinsValue.text = player!!.wins.toString()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun shareContent() {
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
        when (item.itemId) {
            R.id.share_menu -> {
                shareContent() // Panggil fungsi shareContent
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}

