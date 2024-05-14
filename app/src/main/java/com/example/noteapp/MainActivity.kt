package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the button click to navigate to LoginActivity
        binding.firstButton.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // Load the GIF into the ImageView using Glide
        Glide.with(this)
            .asGif()
            .load("https://media.giphy.com/media/RMB8xejcgnmNYnLEgf/giphy.gif")
            .into(binding.imageView2) // Make sure your ImageView ID is correctly referenced
    }
}
