package com.example.townboard2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var buttonLogIn : Button
    private lateinit var buttonSignUp : Button

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.emailTextView)
        password = findViewById(R.id.passwordTextView)
        buttonLogIn = findViewById(R.id.buttonLogIn)
        buttonSignUp = findViewById(R.id.buttonSignUp)


        buttonSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            finish()
            startActivity(intent)

        }

        buttonLogIn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if (email.length >0 && password.length >0){
            login(email, password)
            }
        }
    }

    private fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {  task ->
                if(task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "User does not exist!", Toast.LENGTH_SHORT).show()
                }
        }
    }


}