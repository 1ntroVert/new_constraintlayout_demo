package ru.shlauzer.newconstraintlayoutdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DemosAdapter(private val items: List<Demo>) :
    RecyclerView.Adapter<DemosAdapter.ViewHolder>() {

    data class Demo(
        val title: String,
        val description: String,
        val clickAction: () -> Unit
    )

    class ViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        val title = layout.findViewById(R.id.title) as TextView
        val description = layout.findViewById(R.id.description) as TextView
        var clickAction: (() -> Unit)? = null

        init {
            layout.setOnClickListener { clickAction?.invoke() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row,
            parent,
            false
        ) as ConstraintLayout
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.description.text = items[position].description
        holder.clickAction = items[position].clickAction
    }

    override fun getItemCount() = items.size
}