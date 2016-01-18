package com.example.jasiekpor.restclientapp2

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.widget.EditText
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity(), GlobalActivity{

    override fun getResults(places:List<PlaceRating>) {
        recyclerView.adapter = PlaceAdapter(places)
        //throw UnsupportedOperationException()
    }


    val recyclerView by lazy { findViewById(R.id.recycler_view) as RecyclerView}
    val editText by lazy { findViewById(R.id.edit_text)as EditText }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPlace = editText.text.toString()
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        recallAPI(userPlace)

        editText.addTextChangedListener(object:android.text.TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                recallAPI(editText.text.toString())
                //throw UnsupportedOperationException()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //throw UnsupportedOperationException()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //throw UnsupportedOperationException()
            }

        })

    }
    fun recallAPI(place:String){
        val callToAPI = CallToAPI(this)
        val string = URLEncoder.encode(place,"UTF-8").replace("+","%20")
        val urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=52.2398936,20.9880451&radius=5000&name=$string&key=%20AIzaSyC8Cl3TYbzkZ6bb8_fwKeMhFvx_Be6B0CY"
        callToAPI.execute(URL(urlString))
    }
    override fun handleException(e : Exception){
        val snackBar = Snackbar.make(findViewById(R.id.linear_layout),e.message,Snackbar.LENGTH_LONG)
        editText.setText("No connection!")
        snackBar.show()
    }
}
