package com.allikawa.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_song_list.*
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val allSongs = SongDataProvider.getAllSongs().toMutableList()

        val songAdapter = SongAdapter(allSongs)

        songAdapter.onPersonClickListener = { name, artist, position ->
            // Toast.makeText(this, "My name is: $name at $position", Toast.LENGTH_SHORT).show()
            val tvCurrentTrack= findViewById<TextView>(R.id.tvCurrentTrack)
            tvCurrentTrack.text = "$name - $artist"
        }

        btnShuffle.setOnClickListener {
            val newSongs = allSongs.apply {
                shuffle()
            }
            songAdapter.change(newSongs)
        }

        rvSongs.adapter = songAdapter
    }
}
