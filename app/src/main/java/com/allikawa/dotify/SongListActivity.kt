package com.allikawa.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val listOfSongs = listOf(
            "sldkfjslkj",
            "leo",
            "maeslkj",
            "clindtSDF",
            "testtest",
            "fucsdf",
            "sldfkjs",
            "asldfjsdl",
            "clindtSDF",
            "testtest",
            "fucsdf",
            "sldfkjs",
            "asldfjsdl"
        )

        val songAdapter = SongAdapter(listOfSongs)

        rvSongs.adapter = songAdapter
    }
}
