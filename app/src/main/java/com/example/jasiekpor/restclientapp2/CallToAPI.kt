package com.example.jasiekpor.restclientapp2

import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Created by jasiekpor on 15.01.2016.
 */
class CallToAPI : AsyncTask<URL, Int, List<PlaceRating>>() {
    companion object {
        private val nameKey = "name"
        private val ratingKey = "rating"
    }

    override fun doInBackground(vararg params: URL?): List<PlaceRating>? {
        for (url in params) {
            return readStream(startConnection(url!!))
        }

        throw UnsupportedOperationException()
    }

    private fun startConnection(url: URL): BufferedInputStream {
        val urlConnection = url.openConnection() as HttpURLConnection
        var inputStream: BufferedInputStream? = null
        try {
            inputStream = BufferedInputStream(urlConnection.inputStream)

        } finally {
            urlConnection.disconnect()
        }
        return inputStream!!
    }

    private fun readStream(inputStream: BufferedInputStream): List<PlaceRating> {
        val inputStreamReader = inputStream.reader(Charsets.UTF_8)
        val jsonArray = JSONArray(inputStreamReader.readText())
        val places: ArrayList<PlaceRating> = ArrayList<PlaceRating>()
        for (i in 0..jsonArray.length()) {
            val placeName: String = jsonArray.getJSONObject(i).getString(nameKey)
            val placeRating: Double = jsonArray.getJSONObject(i).getDouble(ratingKey)
            places.add(PlaceRating(placeName, placeRating))
        }
        return places
    }
}