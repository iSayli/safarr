package com.project.safarr

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {

    lateinit var textLogin: TextView
    lateinit var btnSignUp: Button
    lateinit var etUsername: EditText
    lateinit var etRegisterEmail: EditText
    lateinit var etRegisterPassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        etUsername = findViewById(R.id.etUsernameText)
        etRegisterEmail = findViewById(R.id.etRegisterEmailText)
        etRegisterPassword = findViewById(R.id.etRegisterPasswordText)
        btnSignUp = findViewById(R.id.btnSignUp)
        textLogin = findViewById(R.id.txtLogin)

        textLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnSignUp.setOnClickListener{
            if(checking()){
                val email = etRegisterEmail.text.toString()
                val password = etRegisterPassword.text.toString()
                val username = etUsername.text.toString()
                val user = hashMapOf(
                    "Username" to username,
                    "Email" to email
                )

                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("Email",email).get().addOnSuccessListener {
                    task ->
                    if(task.isEmpty){
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                                task ->
                            if(task.isSuccessful){
                                Users.document(email).set(user)
                                val intent = Intent( this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(
                                    this,
                                    "Sign Up Unsuccessful",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }
                    }
                    else{
                        Toast.makeText(
                            this,
                            "User already registered",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent( this, LoginActivity::class.java)
                        startActivity(intent)

                    }

                }

            }
            else{
                Toast.makeText(
                    this,
                    "Enter all the details",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }

     private fun checking(): Boolean {
        if(etRegisterEmail.text.toString().trim{it<=' '}.isNotEmpty()
            && etRegisterPassword.text.toString().trim{it<=' '}.isNotEmpty()
            && etUsername.text.toString().trim{it<=' '}.isNotEmpty())
            {
                return true
            }
        return false
    }

}