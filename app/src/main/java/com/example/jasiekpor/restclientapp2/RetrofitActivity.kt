package com.example.jasiekpor.restclientapp2

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitActivity : AppCompatActivity() {

    val recyclerView by lazy { findViewById(R.id.recycler_view) as RecyclerView }
    val editText by lazy { findViewById(R.id.edit_text)as EditText }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(NearPlacesApi::class.java)
        val call = service.listPlaces(editText.text.toString())
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        call.enqueue(object:retrofit2.Callback<NearPlacesResponse>{
            override fun onResponse(response: retrofit2.Response<NearPlacesResponse>) {
                if(response.isSuccess){
                    recyclerView.adapter = PlaceAdapter( response.body().results)
                }

               // throw UnsupportedOperationException()
            }

            override fun onFailure(t: Throwable) {
                handleException(t)
                //throw UnsupportedOperationException()
            }

        })
    }
    fun handleException(t : Throwable){
        val snackBar = Snackbar.make(findViewById(R.id.linear_layout),t.message,Snackbar.LENGTH_INDEFINITE)
        editText.setText("No connection!")
        snackBar.show()
    }

}


