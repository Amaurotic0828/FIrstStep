package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.firststep.UserHandler.MyWebViewClient

class ScienceActivity : AppCompatActivity() {
    private lateinit var sciencePageWebView : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science)
        sciencePageWebView = findViewById(R.id.sciencePageWV)
        val webSettings: WebSettings = sciencePageWebView.settings
        webSettings.javaScriptEnabled = true
        sciencePageWebView.loadUrl("https://www.ck12.org/assessment/ui/browse/practice/earth-science?topicHandle=introduction-to-earth-science")
        sciencePageWebView.webViewClient = MyWebViewClient(this)


    }//end of onCreate

    override fun onBackPressed(){
        if(sciencePageWebView.canGoBack()){
            sciencePageWebView.goBack()
        }else {
            super.onBackPressed()
        }

    }override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
            R.id.go_to_science_activity -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }//end of item.itemId
    }//end of onOptionsItemSelected
}
