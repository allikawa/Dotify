package com.allikawa.dotify.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.allikawa.dotify.R
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnPlay
import kotlinx.android.synthetic.main.activity_main.ivAlbumCover
import kotlinx.android.synthetic.main.activity_main.tvArtistName
import kotlinx.android.synthetic.main.activity_main.tvSongTitle
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    private var song: Song? = null
    private var currentPlayNum: Int? = null
    private val randomNumber = Random.nextInt(1000, 10000)

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val ARG_SONG = "arg_song"
        const val ARG_SONG_NUM = "arg_song_num"

        fun getInstance(song: Song, playNum: Int) = NowPlayingFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SONG, song)
                putInt(ARG_SONG_NUM, playNum)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)
            val playNum = args.getInt(ARG_SONG_NUM)

            if (song != null) {
                this.song = song
            }

            args.getInt(ARG_SONG_NUM)?.let { playNum ->
                currentPlayNum = playNum
            }

//            if (savedInstanceState != null) {
//                with(savedInstanceState) {
//                    randomNumber = getInt(ARG_SONG_NUM, -1)
//                    // tvNum.text = randomNumber.toString() + " plays"
//                }
//            }
        }

//        btnRandPlay.setOnClickListener {
//            // val tvPlayNum = findViewById<TextView>(R.id.tvPlayNum)
//            val justNum = tvRandNum.text.toString().removeSuffix(" plays")
//            val playNum = justNum.toInt() + 1
//            tvRandNum.text = playNum.toString() + " plays"
//        }
    }


    fun updateSong(song: Song) {
        this.song = song
        updateSongViews()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPlay.setOnClickListener {
            changePlayNum()
        }

        updateSongViews()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt(ARG_SONG_NUM, randomNumber)
//        super.onSaveInstanceState(outState)
//    }

    fun changePlayNum() {
//        val tvPlayNum = findViewById<TextView>(R.id.tvPlayNum)
        val justNum = tvPlayRandNum.text.toString().removeSuffix(" plays")
        val playNum = justNum.toInt() + 1
        tvPlayRandNum.text = playNum.toString() + " plays"
//        tvPlayRandNum.text = "test"
        Log.i(TAG,"this should be updating the numbers")
    }

    private fun updateSongViews() {
        song?.let {
            tvArtistName.text = it.artist
            tvSongTitle.text = it.title
            ivAlbumCover.setImageResource(it.largeImageID)
        }
    }

//    private fun updatePlayNum() {
//        tvNum.text = "text"
//    }

}
