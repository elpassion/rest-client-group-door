package com.example.jasiekpor.restclientapp2

import android.os.AsyncTask
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

/**
 * Created by jasiekpor on 15.01.2016.
 */
class CallToAPI : AsyncTask<URL,Int,Long>(){

    companion object{
        fun startConnection(url:URL){
        val urlConnection = url.openConnection() as HttpURLConnection
            try{
                val inputStream = BufferedInputStream(urlConnection.inputStream)

            }finally{
                urlConnection.disconnect()
            }
        }
    }

    override fun doInBackground(vararg params: URL?): Long? {

        throw UnsupportedOperationException()
    }
    private fun readStream(inputStream:BufferedInputStream){

    }
}