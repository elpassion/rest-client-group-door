package com.example.jasiekpor.restclientapp2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import java.net.URL

class MainActivity : AppCompatActivity() {
    companion object{
        private val url = URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=52.2398936,20.9880451&radius=5000&name=piwpaw&key=%20AIzaSyC8Cl3TYbzkZ6bb8_fwKeMhFvx_Be6B0CY")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById(R.id.edit_text) as EditText

        val callToAPI = CallToAPI(findViewById(R.id.recycler_view)as RecyclerView)

        callToAPI.execute(url)

    }

}
