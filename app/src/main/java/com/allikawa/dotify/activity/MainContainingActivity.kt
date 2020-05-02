package com.allikawa.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.allikawa.dotify.R
import com.allikawa.dotify.fragment.NowPlayingFragment
import com.allikawa.dotify.fragment.OnSongClickListener
import com.allikawa.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlin.random.Random

class MainContainingActivity : AppCompatActivity(), OnSongClickListener {

    companion object {
        private const val PLAY_COUNT = "play_count"
    }

    private lateinit var listOfSongs: List<Song>
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_containing)

        listOfSongs = SongDataProvider.getAllSongs().toMutableList()

        if (supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) == null) {
            val listFragment = SongListFragment.getInstance(listOfSongs)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, listFragment, SongListFragment.TAG)
                .addToBackStack(SongListFragment.TAG)
                .commit()
        }

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
            nowPlayingFragment = NowPlayingFragment.getInstance(song, randomNumber)

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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAY_COUNT, randomNumber)
        super.onSaveInstanceState(outState)
    }
}
