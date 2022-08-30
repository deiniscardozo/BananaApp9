package com.overcom.bananaapp9.ui.view.modules.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.ui.view.modules.thirds.adapter.ProductViewHolder

class ProductAdapter(private val listThirds: List<ThirdsData>,
                     private val onClickListener:(ThirdsData) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(
            R.layout.item_thirds, parent
            , false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = listThirds[position]
        holder.render(item, onClickListener)

    }
    override fun getItemCount(): Int = listThirds.size
}