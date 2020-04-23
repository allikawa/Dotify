package com.allikawa.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val listOfSongs: List<String>): RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songName = listOfSongs[position]
        holder.bind(songName)
    }

    class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName)}

        fun bind(name: String) {
            tvSongName.text = name
        }
    }
}