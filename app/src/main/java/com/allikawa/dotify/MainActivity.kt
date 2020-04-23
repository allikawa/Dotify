package com.allikawa.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        const val SONG_KEY = "SONG_KEY"
    }

    private val randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val song = intent.getParcelableExtra<Song>(SONG_KEY)

        tvSongTitle.text = song.title
        tvArtistName.text = song.artist
        ivAlbumCover.setImageResource(song.largeImageID)

        val tvPlayNum = findViewById<TextView>(R.id.tvPlayNum)
        tvPlayNum.text = randomNumber.toString() + " plays"
    }

    fun changeClicked(view: View) {

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val btnChange = findViewById<Button>(R.id.btnChange)
        val etUsername = findViewById<EditText>(R.id.etUsername)

        if (btnChange.text == "Change User") {
            tvUsername.setVisibility(View.INVISIBLE)
            etUsername.setVisibility(View.VISIBLE)
            val inputedName = etUsername.text.toString()
            btnChange.text = "Apply"
        } else {
            btnChange.text = "Change User"
            tvUsername.setText(etUsername.text.toString())
            tvUsername.setVisibility(View.VISIBLE)
            etUsername.setVisibility(View.INVISIBLE)
        }
    }

    fun changePlayNum(view: View) {
        val tvPlayNum = findViewById<TextView>(R.id.tvPlayNum)
        val justNum = tvPlayNum.text.toString().removeSuffix(" plays")
        val playNum = justNum.toInt() + 1
        tvPlayNum.setText(playNum.toString() + " plays")
    }

    fun toastPrevious(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun toastNext(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

