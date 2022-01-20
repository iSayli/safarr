package com.project.safarr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgotPassword: TextView
    lateinit var textSignUp: TextView
    val validEmail = "sayli2402pednekar@gmail.com"
    val validPassword = "root"

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.etEmailText)
        etPassword = findViewById(R.id.etPasswordText)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        textSignUp = findViewById(R.id.txtSignUp)

        btnLogin.setOnClickListener {

            if(checking()){
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        val intent = Intent( this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Unsuccessful",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }
            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Enter all the details",
                    Toast.LENGTH_LONG
                ).show()

            }
        }

/*
        btnLogin.setOnClickListener{
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if((email==validEmail)&&(password==validPassword)){
                val intent = Intent( this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Email or password entered is incorrect",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
*/

        textSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun checking(): Boolean {
        if(etEmail.text.toString().trim{it<=' '}.isNotEmpty() && etPassword.text.toString().trim{it<=' '}.isNotEmpty()){
            return true
        }
        return false
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}