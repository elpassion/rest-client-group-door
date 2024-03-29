package com.example.jasiekpor.restclientapp2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


/**
 * Created by jasiekpor on 18.01.2016.
 */
interface NearPlacesApi{
    @GET("place/nearbysearch/json?location=52.2398936,20.9880451&radius=5000&key=%20AIzaSyC8Cl3TYbzkZ6bb8_fwKeMhFvx_Be6B0CY")
    fun listPlaces(@Query("name") name : String): Observable<NearPlacesResponse>

}