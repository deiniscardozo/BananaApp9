package com.overcom.bananaapp9.ui.view.modules.thirds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.R

class ThirdsAdapter(private val listThirds: MutableList<ThirdsData>,
                    private val onClickListener:(ThirdsData) -> Unit) :
    RecyclerView.Adapter<ThirdsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ThirdsViewHolder(layoutInflater.inflate(
            R.layout.item_thirds, parent
            , false))
    }

    override fun onBindViewHolder(holder: ThirdsViewHolder, position: Int) {
        val item = listThirds[position]
        holder.render(item, onClickListener)

    }
    override fun getItemCount(): Int = listThirds.size

    fun setItems(list: List<ThirdsData>) {
        listThirds.clear()
        listThirds.addAll(list)
        notifyDataSetChanged()
    }
}