package com.example.jasiekpor.restclientapp2

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.widget.EditText
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.Observable
import rx.Subscription

val retrofit by lazy{
    Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
}
fun <T> Observable<T>.applySchedulers():Observable<T>{
    return this.subscribeOn(rx.schedulers.Schedulers.io())
            .unsubscribeOn(rx.schedulers.Schedulers.io())
            .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
}
class RetrofitActivity : AppCompatActivity() {

    private var subscription: Subscription? = null


    val recyclerView by lazy { findViewById(R.id.recycler_view) as RecyclerView }
    val editText by lazy { findViewById(R.id.edit_text)as EditText }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        callApi(editText.text.toString())
        editText.addTextChangedListener(object:android.text.TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                callApi(editText.text.toString())
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

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
    }

    private fun handleError(t: Throwable) {
        val snackBar = Snackbar.make(findViewById(R.id.linear_layout), t.message, Snackbar.LENGTH_INDEFINITE)
        editText.setText("No connection!")
        snackBar.show()
    }

    private fun displayResults(results: List<PlaceRating>) {
        recyclerView.adapter = PlaceAdapter(results)
    }
    private fun callApi(place : String){
        val api = retrofit.create(NearPlacesApi::class.java)
        subscription = api.listPlaces(place)
                .applySchedulers()
                .subscribe({
                    displayResults(it.results)
                }, {
                    handleError(it)
                })
    }
}


