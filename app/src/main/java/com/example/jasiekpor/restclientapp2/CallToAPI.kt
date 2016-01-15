package com.example.jasiekpor.restclientapp2

import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.style.ReplacementSpan
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Created by jasiekpor on 15.01.2016.
 */
class CallToAPI(val recyclerView : RecyclerView ) : AsyncTask<URL, Int, List<PlaceRating>>() {
    companion object {
        private val nameKey = "name"
        private val ratingKey = "rating"
        private val resultsKey = "results"
        private val noResult = 0.0
    }


    override fun doInBackground(vararg params: URL?): List<PlaceRating>? {
        for (url in params) {
            return startConnection(url!!)
        }

        throw UnsupportedOperationException()
    }

    override fun onPostExecute(result: List<PlaceRating>?) {
        super.onPostExecute(result)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = PlaceAdapter(result!!)
    }

    private fun startConnection(url: URL): List<PlaceRating> {
        val urlConnection = url.openConnection() as HttpURLConnection
        var inputStream: BufferedInputStream? = null
        var places :List<PlaceRating>?= null
        try {
            inputStream = BufferedInputStream(urlConnection.inputStream)
            places = readStream(inputStream)
        } finally {
            urlConnection.disconnect()
        }
        return places!!
    }

    private fun readStream(inputStream: BufferedInputStream): List<PlaceRating> {
        val inputStreamReader = inputStream.reader(Charsets.UTF_8)
        val jsonObject = JSONObject(inputStreamReader.readText())
        val places: ArrayList<PlaceRating> = ArrayList<PlaceRating>()
        val results :JSONArray = jsonObject.get(resultsKey) as JSONArray
        for (i in 0..results.length()-1) {
            val placeName: String = results.getJSONObject(i).getString(nameKey)
            var placeRating : Double = 0.0
            if(!results.getJSONObject(i).isNull(ratingKey)) {
                placeRating = results.getJSONObject(i).getDouble(ratingKey)
            }

            places.add(PlaceRating(placeName, placeRating))
        }
        return places
    }
}