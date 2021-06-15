package com.rajpurohit.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.net.toUri
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

var url :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMene()

    }

    fun loadMene() {
        //val queue = Volley.newRequestQueue(this)
        url = "https://meme-api.herokuapp.com/gimme"
        // val url = "https://www.google.com"
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val memeImageView = findViewById<ImageView>(R.id.memeImageView)
        progressbar.visibility = View.VISIBLE

        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val url = response.getString("url")
                    Glide.with(this).load(url).into(memeImageView)
                    progressbar.visibility = View.GONE

                },
                {
                    progressbar.visibility = View.GONE
                    Toast.makeText(this, "something goes wrong", Toast.LENGTH_LONG).show()
                })

        val queue = Volley.newRequestQueue(this)
        queue.add(jsonObjectRequest)
        /* val stringRequest = StringRequest( Request.Method.GET,url, { response ->
             Log.d("key",response)},
                 {
                     Toast.makeText(this, "some thing wrong", Toast.LENGTH_SHORT).show()

         })
         queue.add(stringRequest)*/

    }

    fun shareMene(view: View) {
         intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"$url")
      // val chooser = Intent.createChooser(intent,"share this meme using...")
        startActivity(intent)

    }
    fun nextMene(view: View) {
        loadMene()
    }
}