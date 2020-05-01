package com.allikawa.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.allikawa.dotify.R
import com.allikawa.dotify.fragment.NowPlayingFragment
import com.allikawa.dotify.fragment.OnSongClickListener
import com.allikawa.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class MainContainingActivity : AppCompatActivity(), OnSongClickListener {

    // private val initialSongs = SongDataProvider.getAllSongs().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_containing)

        val nowPlayingFragment = NowPlayingFragment()
        val song = intent.getParcelableExtra<Song>(MainActivity.SONG_KEY)

        val argumentBundle = Bundle().apply {
            putParcelable(NowPlayingFragment.ARG_SONG, song)
        }

        nowPlayingFragment.arguments = argumentBundle

        val songListFragment = SongListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()
    }

    override fun onSongClicked(song: Song) {
        val nowPlayingFragment = NowPlayingFragment()
        // val song = intent.getParcelableExtra<Song>(MainActivity.SONG_KEY)
        val argumentBundle = Bundle().apply {
            putParcelable(NowPlayingFragment.ARG_SONG, song)
        }
        nowPlayingFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, nowPlayingFragment)
            .commit()
    }

//    fun showNowPlayingFragment(song: Song) {
//        val nowPlayingFragment = NowPlayingFragment()
//        // val song = intent.getParcelableExtra<Song>(MainActivity.SONG_KEY)
//        val argumentBundle = Bundle().apply {
//            putParcelable(NowPlayingFragment.ARG_SONG, song)
//        }
//        nowPlayingFragment.arguments = argumentBundle
//
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragContainer, nowPlayingFragment)
//            .commit()
//    }
}
