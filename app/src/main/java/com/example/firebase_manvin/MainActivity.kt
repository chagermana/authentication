package com.example.firebase_manvin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var Edtemail: EditText
    lateinit var Edtpass: EditText
    lateinit var Edtconfirmpass: EditText
    lateinit var Btnsignup: Button
    lateinit var TvDirecttoLogin: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Edtemail = findViewById(R.id.etSEmailAddress)
        Edtpass = findViewById(R.id.etSConfPassword)
        Edtconfirmpass = findViewById(R.id.etSConfPassword)
        Btnsignup = findViewById(R.id.btnSSigned)
        TvDirecttoLogin = findViewById(R.id.tvRedirectLogin)

        auth = Firebase.auth

        TvDirecttoLogin.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        Btnsignup.setOnClickListener {

        }
        fun Signup() {
            val email = Edtemail.text.toString()
            val pass = Edtpass.text.toString()
            val confirmpass = Edtconfirmpass.text.toString()

            if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()) {
                Toast.makeText(this, "please enter email and password", Toast.LENGTH_LONG)
                return

            } else if (pass != confirmpass) {
                Toast.makeText(this, "password dosent match", Toast.LENGTH_LONG)
                return
            }

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "signed in successfully", Toast.LENGTH_LONG)
                } else {
                    Toast.makeText(this, "failed to create", Toast.LENGTH_LONG)
                }

            }

        }
    }
}