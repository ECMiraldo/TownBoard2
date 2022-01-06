package com.example.townboard2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.townboard2.datatypes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var buttonSignUp : Button
    private lateinit var message : TextView


    private lateinit var auth : FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        name = findViewById(R.id.nameSignUpTextView)
        email = findViewById(R.id.emailSignUpTextView)
        password = findViewById(R.id.passwordSignUpTextView)
        buttonSignUp = findViewById(R.id.registerButtonSignUp)
        message = findViewById(R.id.messageSignUpTextView)


        buttonSignUp.setOnClickListener{
            val name = name.text.toString()
            //GET CHAT ROOM WHERE USER WANTS TO GO
            val email = email.text.toString()
            val password = password.text.toString()

            if(name.length >1 && email.length > 5 && password.length >5) {
                signUp(name, email, password)
            } else{
                message.isVisible = true
                message.text = "Por favor insira informação válida"
            }
        }
    }

    private fun signUp(name : String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, auth.currentUser?.uid!!)
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUpActivity, "Insira informaão corretos", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name : String, email : String, uid : String) {
        db.collection("user").add(User(name, email, uid))
    }



}


