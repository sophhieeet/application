package com.example.myfinalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var signUp :Button
    private lateinit var createEmail :EditText
    private lateinit var createPassword :EditText

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        init()
        listener()

    }

    private fun init(){
        signUp = findViewById(R.id.signUpButton)
        createPassword = findViewById(R.id.createPassword)
        createEmail = findViewById(R.id.createEmail)
    }

    private fun listener(){
        signUp.setOnClickListener {
            val email = createEmail.text.toString()
            val password = createPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this, BottomNavigationActivity::class.java ))
                }
            }
        }
    }
}