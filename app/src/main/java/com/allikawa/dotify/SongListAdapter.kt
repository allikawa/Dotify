package com.allikawa.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongAdapter(initListOfSongs: List<Song>): RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    private var listOfSongs: List<Song> = initListOfSongs.toList()
    var onSongSelected: ((song: Song) -> Unit)? = null
    var onLongSongClickListener: ((song: Song, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songName = listOfSongs[position]
        holder.bind(songName, position)
    }

    fun change(newSongs: List<Song>) {
        val callback = SongDiffCallback(listOfSongs, newSongs)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
    }

    fun remove(newSongs: List<Song>) {
        listOfSongs = newSongs
        notifyDataSetChanged()
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName)}
        private val tvArtistName by lazy { itemView.findViewById<TextView>(R.id.tvArtistName)}
        private val ivAlbumCover by lazy { itemView.findViewById<ImageView>(R.id.ivAlbumCover)}

        fun bind(song: Song, position: Int) {
            tvSongName.text = song.title
            tvArtistName.text = song.artist
            ivAlbumCover.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongSelected?.invoke(song)
            }

            itemView.setOnLongClickListener {
                onLongSongClickListener?.invoke(song, position)
                true
            }
        }
    }
}