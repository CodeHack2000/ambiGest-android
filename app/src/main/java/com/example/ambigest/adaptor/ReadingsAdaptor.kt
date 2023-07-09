package com.example.ambigest.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ambigest.R
import com.example.ambigest.model.ReadingsModel

class ReadingsAdaptor(private val dataset: List<ReadingsModel>): RecyclerView.Adapter<ReadingsAdaptor.ReadingsAdaptorViewHolder>() {
    class ReadingsAdaptorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.itemReadings_tv_date)
        val amount: TextView = view.findViewById(R.id.itemReadings_tv_value)
        val deleteBtn: Button = view.findViewById(R.id.itemReadings_btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingsAdaptorViewHolder {
        val viewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_reading, parent, false)
        return ReadingsAdaptorViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ReadingsAdaptorViewHolder, position: Int) {
        val item = dataset[position]

        holder.date.text = item.date
        holder.amount.text = item.amount.toString()

        // TODO: When click this button, this item needs to be deleted with API request
        holder.deleteBtn.setOnClickListener {

        }
    }
}