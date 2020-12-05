package com.example.firststep

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.firststep.UserHandler.UserHandler
import com.example.firststep.UserHandler.userDB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var fnameEditText: EditText
    lateinit var lnameEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var ageTextView: TextView
    lateinit var passwordEditText: EditText
    lateinit var registerButton: Button
    lateinit var userHandler: UserHandler
    lateinit var loginTV: TextView
    lateinit var fAuth: FirebaseAuth
    private var firebaseUserID: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fnameEditText = findViewById(R.id.fnameET)
        lnameEditText = findViewById(R.id.lnameET)
        emailEditText = findViewById(R.id.emailET)
        ageTextView = findViewById(R.id.progressView)
        passwordEditText = findViewById(R.id.passwordET)
        registerButton = findViewById(R.id.regBtn)
        userHandler = UserHandler()
        loginTV = findViewById(R.id.loginTV)
        loginTV.setOnClickListener{startActivity(Intent(applicationContext, LoginActivity::class.java))}
        fAuth = Firebase.auth


        registerButton.setOnClickListener{

            val fname = fnameEditText.text.toString()
            val lname = lnameEditText.text.toString()
            val email = emailEditText.text.toString()
            val age = ageTextView.text.toString()
            val password = passwordEditText.text.toString()
            val userlogin = userDB(fname = fname, lname = lname, email = email, age = age, password = password)
            if (email == ""){
                Toast.makeText(applicationContext,"Please enter Email Address",Toast.LENGTH_SHORT).show()
            }
             else if (userHandler.create(userlogin)){
                Toast.makeText(applicationContext,"Register Successfully", Toast.LENGTH_SHORT).show()
                fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            firebaseUserID = fAuth.currentUser!!.uid
                            userHandler.database.reference.child("Users").child(firebaseUserID)
                            val userHashMap = HashMap<String, Any>()
                            userHashMap["uid"] = firebaseUserID
                            userHashMap["email"] = email
                            userHashMap["status"] = "offline"
                            userHashMap["search"] = email.toLowerCase()


                        userHandler.database.reference.updateChildren(userHashMap)
                            .addOnCompleteListener{task ->
                                if(task.isSuccessful){
                                    val intent = Intent(applicationContext, com.example.firststep.ListActivity::class.java)
                                    val email = findViewById<EditText>(R.id.emailET).text.toString()
                                    val age = findViewById<TextView>(R.id.progressView).text.toString()
                                    val fname = findViewById<EditText>(R.id.fnameET).text.toString()
                                    val lname = findViewById<EditText>(R.id.lnameET).text.toString()

                                    intent.putExtra("fname", fname)
                                    intent.putExtra("lname", lname)
                                    intent.putExtra("email", email)
                                    intent.putExtra("age", age)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                        }
                        else{
                            Toast.makeText(applicationContext,"Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        val slider = findViewById<SeekBar>(R.id.seekBar)
        val value = findViewById<TextView>(R.id.progressView)

        slider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text = progress.toString()
            }//end of onProgressChanged
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }//end of onStartTrackingTouch
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }//end of onStopTrackingTouch
        })//end of setOnSeekBarChangeListener
        this.actionBar?.hide()
        this.supportActionBar?.hide()
    }//end of onCreate




}//end of class