package com.allikawa.dotify.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_song_list.*
import android.widget.Toast
import com.allikawa.dotify.R
import com.allikawa.dotify.SongAdapter
import com.allikawa.dotify.activity.MainActivity.Companion.SONG_KEY
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val allSongs = SongDataProvider.getAllSongs().toMutableList()

        val songAdapter = SongAdapter(allSongs)

        songAdapter.onSongSelected = { song: Song ->
            val tvCurrentTrack= findViewById<TextView>(R.id.tvCurrentTrack)
            tvCurrentTrack.text = "${song.title} - ${song.artist}"

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(SONG_KEY, song)

            flPlay.setOnClickListener {
                startActivity(intent)
            }
        }

        songAdapter.onLongSongClickListener = {song, position ->
            val newSongs = allSongs.apply {
                removeAt(position)
                Toast.makeText(this@SongListActivity,"You deleted ${song.title}", Toast.LENGTH_SHORT).show()
            }
            songAdapter.remove(newSongs)
        }

        btnShuffle.setOnClickListener {
            val shuffledList = allSongs.shuffled()
            songAdapter.change(shuffledList)
        }

        rvSongs.adapter = songAdapter
    }
}
