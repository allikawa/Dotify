package com.allikawa.dotify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongAdapter(initListOfSongs: List<Song>): RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    private var listOfSongs: List<Song> = initListOfSongs.toList()
    var onSongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songName = listOfSongs[position]
        holder.bind(songName)
    }

    fun change(newSongs: List<Song>) {
        val callback = SongDiffCallback(listOfSongs, newSongs)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName)}
        private val tvArtistName by lazy { itemView.findViewById<TextView>(R.id.tvArtistName)}
        private val ivAlbumCover by lazy { itemView.findViewById<ImageView>(R.id.ivAlbumCover)}

        fun bind(song: Song) {
            tvSongName.text = song.title
            tvArtistName.text = song.artist
            ivAlbumCover.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }
        }
    }
}