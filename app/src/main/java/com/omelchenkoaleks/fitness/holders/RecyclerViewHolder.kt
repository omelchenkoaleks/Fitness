package com.omelchenkoaleks.fitness.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.interfaces.ItemClickListener

/**
 * Суть паттерна ViewHolder заключается в том, что для каждого элемента списка создаётся объект,
 * хранящий ссылки на отдельные вьюхи внутри элемента.
 */
class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var image: ImageView? = null
    var text: TextView? = null

    init {
        image = itemView.findViewById(R.id.img_exercise)
        text = itemView.findViewById(R.id.name_exercise)

        itemView.setOnClickListener(this)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View) {
        itemClickListener.onClick(v, adapterPosition)
    }

}
