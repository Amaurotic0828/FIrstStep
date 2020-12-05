package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        this.actionBar?.hide()
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.signupBtn).setOnClickListener{gotoRegActivity()}
        findViewById<Button>(R.id.loginBtn).setOnClickListener{
            loginUser()
        }

        fAuth = FirebaseAuth.getInstance()


    }
    private fun loginUser() {
        val email: String = findViewById<EditText>(R.id.emailLoginET).text.toString()
        val password: String = findViewById<EditText>(R.id.passwordLoginET).text.toString()
        if (email == "") {
            Toast.makeText(applicationContext, "Please enter Email Address", Toast.LENGTH_SHORT)
                .show()
        }else if(password == "") {
            Toast.makeText(applicationContext, "Please enter Password", Toast.LENGTH_SHORT)
                .show()
        } else {
            fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Welcome $email!!", Toast.LENGTH_SHORT).show()
                        val intentListActivity = Intent(applicationContext, ListActivity::class.java)
                        val firstname = intent.getStringExtra("fname")
                        val lastname = intent.getStringExtra("lname")
                        val emailadd = intent.getStringExtra("email")
                        val edad = intent.getStringExtra("age")
                        val result = "First Name:\t $firstname" +
                                "\nLast Name: \t $lastname" +
                                "\nEmail Address: \t $emailadd" +
                                "\nAge: \t $edad"
                        intentListActivity.putExtra("result", result)
                        intentListActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intentListActivity)
                        finish()
                    }else {
                        Toast.makeText(applicationContext,"Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun gotoRegActivity(){
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }
}