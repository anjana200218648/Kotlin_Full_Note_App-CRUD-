package com.example.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: LoginDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LoginDataBaseHelper(this)

        binding.register2.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString()
            val age = binding.editTextAge.text.toString()
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            val userDetails = UserDetails(fullName, age, phoneNumber, email, password)
            db.insertDetails(userDetails)
            finish()

            Toast.makeText(this, "Details Saved", Toast.LENGTH_LONG).show()
        }
    }
}