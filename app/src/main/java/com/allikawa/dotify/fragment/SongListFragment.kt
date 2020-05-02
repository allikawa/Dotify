package com.allikawa.dotify.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.allikawa.dotify.R
import com.allikawa.dotify.SongAdapter
import com.allikawa.dotify.activity.MainActivity
import com.allikawa.dotify.activity.MainContainingActivity
import com.allikawa.dotify.activity.SongListActivity
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListFragment: Fragment() {

    private lateinit var songAdapter: SongAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private lateinit var listOfSongs: MutableList<Song>

    private val initialSongs = SongDataProvider.getAllSongs().toMutableList()

    companion object {
        val TAG = SongListFragment::class.java.simpleName

        private const val ARG_LIST_OF_SONGS = "ARG_LIST_OF_SONGS"

        fun getInstance(listOfSongs: List<Song>): SongListFragment {
            return SongListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_LIST_OF_SONGS, ArrayList(listOfSongs))
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            with(args) {
                getParcelableArrayList<Song>(ARG_LIST_OF_SONGS)?.let { listOfSongs ->
                    this@SongListFragment.listOfSongs = listOfSongs.toMutableList()
                }
            }
        }

        arguments?.getParcelableArrayList<Song>(ARG_LIST_OF_SONGS)?.let { listOfSongs ->
            this.listOfSongs = listOfSongs
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val allSongs = SongDataProvider.getAllSongs().toMutableList()

        songAdapter = SongAdapter(listOfSongs)
        rvSongs.adapter = songAdapter

        songAdapter.onSongSelected = { song ->
            //            val tvCurrentTrack= findViewById<TextView>(R.id.tvCurrentTrack)
//            val mainContainingActivity = activity as MainContainingActivity
//            mainContainingActivity.showNowPlayingFragment(song)


            tvCurrentTrack.text = "${song.title} - ${song.artist}"

//            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra(MainActivity.SONG_KEY, song)

            flPlay.setOnClickListener {
                //                startActivity(intent)
                val tempActivityRef = activity
                if (tempActivityRef is OnSongClickListener) {
                    onSongClickListener = tempActivityRef

                }
                onSongClickListener?.onSongClicked(song)

//                mainContainingActivity.showNowPlayingFragment(song)
            }
        }

//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra(MainActivity.SONG_KEY, song)
//
//        flPlay.setOnClickListener {
//            startActivity(intent)
//        }
    }

}

interface OnSongClickListener {
    fun onSongClicked(song: Song)
}