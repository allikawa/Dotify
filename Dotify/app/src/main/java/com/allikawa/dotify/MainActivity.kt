package com.allikawa.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNumber = Random.nextInt(1000, 10000) // exclusive, so not 9999

        val tvPlayNum = findViewById<TextView>(R.id.tvPlayNum)
        tvPlayNum.text = randomNumber.toString()

        // val etUsername = findViewById<TextView>(R.id.etUsername)
        // etUsername.setVisibility(View.GONE)
    }

    fun changeClicked(view: View) { // for the [change user] state btn
        Log.i("allikawa", "submitButtonClicked")

        Toast.makeText(this, "Toast is ready!", Toast.LENGTH_SHORT).show()

        // val btnChange = findViewById<Button>(R.id.btnChange)
        // btnChange.text = "Apply"

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.setVisibility(View.GONE)

        // val etUsername = findViewById<EditText>(R.id.etUsername)
        // val userInputName = etUsername.text.toString()

        // userInputName

    }
}
