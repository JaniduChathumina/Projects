package com.example.tutorial5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EndGame : AppCompatActivity() {

    private lateinit var playAgainButton: Button
    private lateinit var winnerView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        playAgainButton= findViewById(R.id.playAgain)
        winnerView= findViewById(R.id.id_winnerView)

        val winner = intent.getStringExtra("winner")
        winnerView.text=winner

        playAgainButton.setOnClickListener {
            val game = Intent(applicationContext,MainActivity::class.java)
            startActivity(game)
        }
    }
}