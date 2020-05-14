package com.allikawa.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.allikawa.dotify.R
import com.allikawa.dotify.fragment.NowPlayingFragment
import com.allikawa.dotify.fragment.OnSongClickListener
import com.allikawa.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random
import android.content.Intent

class MainContainingActivity : AppCompatActivity(), OnSongClickListener {

    companion object {
        private const val PLAY_COUNT = "play_count"
    }

    private lateinit var listOfSongs: List<Song>
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_containing)

//        getSongs()
        val listofJSONSongs = getSongs().toString()

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

    private fun fetchDataWithGson() {
        val gson = Gson()

        val song: Song = gson.fromJson(singleSongJSONString, Song::class.java)

        song.content?.let {
            tvSongTitle.text = song.title
        }

    }

}
