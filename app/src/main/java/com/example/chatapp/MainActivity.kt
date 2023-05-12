package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val auth = Firebase.auth

    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var bnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVistas()

        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity :: class.java))
        }

        bnLogin.setOnClickListener(this)

        checkuser()

    }

    private fun inicializarVistas() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        bnLogin = findViewById(R.id.btLogin)
    }

    private fun login(){

        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                checkuser()
            } else {
                task.exception?.let {
                    Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkuser(){
        val user = auth.currentUser

        if (user != null){
            val s = Intent(this, ListOfChats::class.java)
            s.putExtra("user", user.email)
            startActivity(s)

            finish()
        }
    }

    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")

        val Email = etEmail.text.toString()
        val Password = etPassword.text.toString()

        when(p0?.id) {
            R.id.btLogin -> {
                if (Email.isEmpty() && Password.isEmpty()) {
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
                }
                else {
                    login()
                }
            }
        }
    }


}