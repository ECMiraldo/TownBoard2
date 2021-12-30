package com.example.townboard2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var buttonSignUp : Button

    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        name = findViewById(R.id.nameTextView)
        email = findViewById(R.id.emailSignUpTextView)
        password = findViewById(R.id.passwordSignUpTextView)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener{
            val name = name.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name : String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, auth.currentUser?.uid!!)
                    val Intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Something happened... We couldnt complete task",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun addUserToDatabase(name : String, email : String, uid : String) {
        database = FirebaseDatabase.getInstance().getReference()

        database.child("user").child(uid).setValue(User(name,email,uid))
    }



}


