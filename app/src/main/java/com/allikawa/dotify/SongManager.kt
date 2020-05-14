package com.allikawa.dotify

import android.content.Context
import com.allikawa.dotify.fragment.OnSongClickListener
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ericchee.songdataprovider.Song
import com.google.gson.Gson

class SongManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getSong(onSongReady: (Song) -> Unit) {
        val songURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

        val request = StringRequest(
            Request.Method.GET, songURL,
            { response ->
                val gson = Gson()
                val song = gson.fromJson(response, Song::class.java)
            }, {

            }
        )

        queue.add(request)

    }

    fun getListOfSongs(onSongReady: (Song) -> Unit, onError: (() -> Unit)? = null) {
        val songURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/emails.json"

        val request = StringRequest(
            Request.Method.GET, songURL,
            { response ->
                val gson = Gson()
                val allSongs = gson.fromJson(response, AllSongs::class.java)

                onSongReady(allSongs)
            },
            {
                onError?.invoke()
            }
        )
        queue.add(request)
    }
}