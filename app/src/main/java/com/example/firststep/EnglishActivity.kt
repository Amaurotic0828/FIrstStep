package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.firststep.UserHandler.MyWebViewClient

class EnglishActivity : AppCompatActivity() {
    private lateinit var englishPageWebView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english)

        englishPageWebView = findViewById(R.id.englishPageWV)
        val webSettings: WebSettings = englishPageWebView.settings
        webSettings.javaScriptEnabled = true
        englishPageWebView.loadUrl("https://www.ck12.org/assessment/ui/browse/practice/spelling?topicHandle=letters-vs.-sounds-and-vowels-vs.-consonants")
        englishPageWebView.webViewClient = MyWebViewClient(this)


    }//end of onCreate

    override fun onBackPressed(){
        if(englishPageWebView.canGoBack()){
            englishPageWebView.goBack()
        }else {
            super.onBackPressed()
        }


    }//end of onBackPressed

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }//end of onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.edit_user_profile ->{
                val gotoResultIntent = Intent(this, UserActivity::class.java)
                val firstname = intent.getStringExtra("fname")
                val lastname = intent.getStringExtra("lname")
                val emailadd = intent.getStringExtra("email")
                val edad = intent.getStringExtra("age")
                val result = "First Name:\t $firstname" +
                        "\nLast Name: \t $lastname" +
                        "\nEmail Address: \t $emailadd" +
                        "\nAge: \t $edad"
                gotoResultIntent.putExtra("result", result)
                startActivity(gotoResultIntent)
                true
            }//end of R.id.edit_user_profile
            R.id.go_to_math_activity ->{
                startActivity(Intent(applicationContext,MathActivity::class.java ))
                true
            }//end of R.id.math_activity
            R.id.go_to_english_activity ->{
                true
            }//end of R.id.english_activity
            R.id.main_activity -> {
                startActivity(Intent(applicationContext,ListActivity::class.java))
                true
            }//end of R.id.main_activity
            R.id.go_to_science_activity -> {
                startActivity(Intent(applicationContext, ScienceActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }//end of item.itemId
    }//end of onOptionsItemSelected
}