package com.example.ambigest.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ambigest.R
import com.example.ambigest.model.InvoicesModel

class InvoicesAdaptor(private val dataset: List<InvoicesModel>): RecyclerView.Adapter<InvoicesAdaptor.InvoicesAdaptorViewHolder>() {
    class InvoicesAdaptorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val month: TextView = view.findViewById(R.id.itemInvoice_tv_month)
        val consumption: TextView = view.findViewById(R.id.itemInvoices_consumptionValue)
        val increase: TextView = view.findViewById(R.id.itemInvoices_increaseValue)
        val total: TextView = view.findViewById(R.id.itemInvoices_totalValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoicesAdaptorViewHolder {
        val viewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_invoice, parent, false)
        return InvoicesAdaptorViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: InvoicesAdaptorViewHolder, position: Int) {
        val item = dataset[position]

        holder.month.text = item.month
        holder.consumption.text = item.consumption.toString()
        holder.increase.text = item.increase.toString()
        holder.total.text = item.total.toString()
    }
}