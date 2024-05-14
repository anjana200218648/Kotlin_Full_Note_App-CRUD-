package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: LoginDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LoginDataBaseHelper(this)

        binding.loginbutton.setOnClickListener {
            val fullName = binding.editTextTextPersonName.text.toString()
            val password = binding.editTextTextPersonName2.text.toString()

            val userDetails = db.getUserDetails(fullName)

            if (userDetails != null && userDetails.password == password) {

                val intent = Intent(this@LoginActivity, MainPageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials!!", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerbutton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}
