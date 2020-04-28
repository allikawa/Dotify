package com.allikawa.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_song_list.*
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.allikawa.dotify.MainActivity.Companion.SONG_KEY
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val allSongs = SongDataProvider.getAllSongs().toMutableList()

        val songAdapter = SongAdapter(allSongs)

        songAdapter.onSongClickListener = { song: Song ->
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
