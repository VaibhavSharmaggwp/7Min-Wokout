package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivitySignUpactivityBinding
import com.google.firebase.auth.FirebaseAuth

class SignUPActivity : AppCompatActivity() {

    private val binding: ActivitySignUpactivityBinding by lazy {
        ActivitySignUpactivityBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Navigate to LoginActivity when the login button is clicked
        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Handle the sign-up process
        binding.buttonSignup.setOnClickListener {

            // Get text from EditText fields
            val email = binding.editTextEmail.text.toString()
            val username = binding.editTextText.text.toString() // This seems to be the username field
            val password = binding.editTextTextPassword2.text.toString()
            val repeatPassword = binding.editTextReenterPassword.text.toString()

            // Check if any field is blank
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()
            } else if (password != repeatPassword) {
                Toast.makeText(this, "Repeat Password must be the same", Toast.LENGTH_LONG).show()
            } else {
                // Create a new user with email and password
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                            // Navigate to LoginActivity after successful registration
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
