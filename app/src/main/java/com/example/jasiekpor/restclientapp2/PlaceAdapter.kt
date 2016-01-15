package com.example.jasiekpor.restclientapp2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.json.JSONArray
import java.util.*

/**
 * Created by jasiekpor on 15.01.2016.
 */
class PlaceAdapter(places: List<PlaceRating>) : RecyclerView.Adapter<ViewHolder>() {
    val places = places

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_object_layout, parent, false)
        return ViewHolder(view)
        throw UnsupportedOperationException()
    }

    override fun getItemCount(): Int {
        return places.size
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.placeTextView!!.text = places.get(position).name
        viewHolder.rateingTextView!!.text = places.get(position).rating.toString()

           //     throw UnsupportedOperationException()
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var placeTextView: TextView? = null
    var rateingTextView: TextView? = null

    init {
        placeTextView = view.findViewById(R.id.place) as TextView
        rateingTextView = view.findViewById(R.id.rating) as TextView
    }


}