package com.example.jasiekpor.restclientapp2

/**
 * Created by jasiekpor on 15.01.2016.
 */
interface GlobalActivity {
    public fun getResults(places:List<PlaceRating>)
    public fun handleException(e: Exception)
}