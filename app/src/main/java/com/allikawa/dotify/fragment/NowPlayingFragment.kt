package com.allikawa.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allikawa.dotify.R
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    private var song: Song? = null

    companion object {
        const val ARG_SONG = "arg_song"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)
            if (song != null) {
                this.song = song
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        tvArtistName.text = song.artist
//        tvSongTitle.text = song.title
//        ivAlbumCover.setImageResource(song.largeImageID)

        song?.let {
            tvArtistName.text = it.artist
            tvSongTitle.text = it.title
            ivAlbumCover.setImageResource(it.largeImageID)
        }

    }

}
