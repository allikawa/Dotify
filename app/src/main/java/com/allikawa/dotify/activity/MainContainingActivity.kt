package com.allikawa.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.allikawa.dotify.R
import com.allikawa.dotify.fragment.NowPlayingFragment
import com.allikawa.dotify.fragment.OnSongClickListener
import com.allikawa.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.fragment_song_list.*

class MainContainingActivity : AppCompatActivity(), OnSongClickListener {

    private lateinit var listOfSongs: List<Song>

    // val initialSongs = SongDataProvider.getAllSongs().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_containing)

        listOfSongs = SongDataProvider.getAllSongs().toMutableList()

//        val nowPlayingFragment = NowPlayingFragment()
//        // val song = intent.getParcelableExtra<Song>(MainActivity.SONG_KEY)
//        val argumentBundle = Bundle().apply {
//            val initialSongs = ArrayList<Song>(SongDataProvider.getAllSongs())
//            putParcelableArrayList(NowPlayingFragment.ARG_SONG, initialSongs)
//        }
//        nowPlayingFragment.arguments = argumentBundle
        if (supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) == null) {
            val listFragment = SongListFragment.getInstance(listOfSongs)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, listFragment, SongListFragment.TAG)
                .addToBackStack(SongListFragment.TAG)
                .commit()
        } else {}

//        val songListFragment = SongListFragment()
//
//        val argumentBundle = Bundle().apply {
//            val initialSongs = ArrayList<Song>(SongDataProvider.getAllSongs())
//            putParcelableArrayList(NowPlayingFragment.ARG_SONG, initialSongs)
//        }
//        songListFragment.arguments = argumentBundle
//
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragContainer, songListFragment)
//            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

    override fun onSongClicked(song: Song) {
        var nowPlayingFragment = getNowPlayingFragment()

        if (nowPlayingFragment == null) {
            nowPlayingFragment = NowPlayingFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.ARG_SONG, song)
            }

            nowPlayingFragment.arguments = argumentBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()
        } else {
            nowPlayingFragment.updateSong(song)
        }

    }
}
