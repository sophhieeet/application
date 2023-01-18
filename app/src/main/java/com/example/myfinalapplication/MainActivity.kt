package com.example.myfinalapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class MainActivity : AppCompatActivity() {
    private lateinit var email :EditText
    private lateinit var password :EditText
    private lateinit var signinbutton :Button
    private lateinit var createAccountButton :Button

    private val firebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        loginListener()


    }

    private fun init(){
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signinbutton = findViewById(R.id.signinbutton)
        createAccountButton = findViewById(R.id.createAccountButton)

    }

    private fun loginListener() {
        signinbutton.setOnClickListener {
            val remail = email.text.toString()
            val rpassword = password.text.toString()

            if (remail.isEmpty() || rpassword.isEmpty()){
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(remail, rpassword).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, BottomNavigationActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }

            }
        }

        createAccountButton.setOnClickListener {
            val remail = email.text.toString()
            val rpassword = password.text.toString()

            if (remail.isEmpty() || rpassword.isEmpty()){
                startActivity(Intent(this, CreateAccountActivity::class.java))



            }
        }
    }
}


