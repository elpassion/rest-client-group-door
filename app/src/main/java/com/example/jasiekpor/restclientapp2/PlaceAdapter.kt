package com.example.jasiekpor.restclientapp2

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.json.JSONArray

/**
 * Created by jasiekpor on 15.01.2016.
 */
class PlaceAdapter : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int {
        throw UnsupportedOperationException()
    }

    companion object {
        var jsonObjects = JSONArray()
        fun setJSONObjects(jsonArray: JSONArray) {
            jsonObjects = jsonArray
        }
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}