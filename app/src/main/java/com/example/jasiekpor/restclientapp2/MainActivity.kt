package com.example.jasiekpor.restclientapp2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.EditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById(R.id.edit_text) as EditText
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView

        
    }

}
