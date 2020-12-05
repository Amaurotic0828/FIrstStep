package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        findViewById<Button>(R.id.changeImgBtn).setOnClickListener{change()}

        val txt = findViewById<TextView>(R.id.resultView)
        val intent = getIntent()
        val output = intent.getStringExtra("result")
        txt.text = output

    }//end of onCreate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }//end of onCreateOptionsMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.edit_user_profile ->{
                true
            }//end of R.id.edit_user_profile
            R.id.go_to_math_activity ->{
                startActivity(Intent(applicationContext, MathActivity::class.java))
                true
            }//end of R.id.math_activity
            R.id.go_to_english_activity ->{
                startActivity(Intent(applicationContext, EnglishActivity::class.java))
                true
            }//end of R.id.english_activity
            R.id.main_activity -> {
                startActivity(Intent(applicationContext,ListActivity::class.java))
                true
            }//end of R.id.main_activity
            else -> super.onOptionsItemSelected(item)
        }//end of item.itemId
    }//end of onOptionsItemSelected

    private fun change(){

        val rand = Random()
        val randomNumber: Int = rand.nextInt(4)
        val iv = findViewById<ImageView>(R.id.imageView)
        iv.setBackgroundResource(resources.obtainTypedArray(R.array.array_logo).getResourceId(randomNumber, 1))
    }

}//end of class