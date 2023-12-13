package com.example.first_mobile_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.first_mobile_application.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()



        // Add click listener for the logout button
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }

        // Set the greeting message with the user's name
        val greetingTextView: TextView = findViewById(R.id.greetingTextView)
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val userEmail = currentUser.email ?: "No email available"
            greetingTextView.text = "Hello, $userEmail!"
        } else {
            greetingTextView.text = "Hello, Guest!"
        }
    }



    private fun logout() {
        // Firebase Authentication sign out
        firebaseAuth.signOut()

        // After logging out, navigate to the SignInActivity
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close the current activity to prevent going back with the back button
    }
}
