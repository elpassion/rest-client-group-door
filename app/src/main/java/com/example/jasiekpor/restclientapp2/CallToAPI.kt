package com.example.jasiekpor.restclientapp2

import android.os.AsyncTask
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Created by jasiekpor on 15.01.2016.
 */
class CallToAPI(val activity: GlobalActivity) : AsyncTask<URL, Int, List<PlaceRating>>() {
    companion object {
        private val nameKey = "name"
        private val ratingKey = "rating"
        private val resultsKey = "results"
    }


    override fun doInBackground(vararg params: URL?): List<PlaceRating>? {
        try {
            return startConnection(params[0]!!)
        } catch(e: Exception) {
            activity.handleException(e)
            return null
        }
        //throw UnsupportedOperationException()
    }

    override fun onPostExecute(result: List<PlaceRating>) {

            activity.getResults(result)

            super.onPostExecute(result)
    }

    private fun startConnection(url: URL): List<PlaceRating> {
        val urlConnection = url.openConnection() as HttpURLConnection
        var inputStream: BufferedInputStream? = null
        var places: List<PlaceRating> =  ArrayList()
        try {
            inputStream = BufferedInputStream(urlConnection.inputStream)
            places = readStream(inputStream)
        }catch(e:Exception){
            activity.handleException(e)
        } finally {
            urlConnection.disconnect()
        }
        return places
    }

    private fun readStream(inputStream: BufferedInputStream): List<PlaceRating> {
        val inputStreamReader = inputStream.reader(Charsets.UTF_8)
        val gson = Gson()
        val response = gson.fromJson(inputStreamReader, Response::class.java)

        return response.results
    }
}