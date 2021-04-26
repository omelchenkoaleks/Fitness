package com.omelchenkoaleks.fitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.holders.RecyclerViewHolder
import com.omelchenkoaleks.fitness.interfaces.ItemClickListener
import com.omelchenkoaleks.fitness.models.Exercise
import com.omelchenkoaleks.fitness.utils.showToast

class RecyclerViewAdapter(exerciseList: List<Exercise>) :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    private var list = emptyList<Exercise>()

    init {
        list = exerciseList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.item_exercise, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.image?.setImageResource(list[position].image_id)
        holder.text?.text = list[position].name

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
                showToast("Click ${list[position]}")
            }
        })
    }

    override fun getItemCount(): Int = list.size

}