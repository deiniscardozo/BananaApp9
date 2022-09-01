package com.overcom.bananaapp9.ui.view.modules.thirds.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.databinding.ItemThirdsBinding
import java.math.RoundingMode


class ThirdsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemThirdsBinding.bind(view)

    fun render(thirdsModel: ThirdsData, onClickListener:(ThirdsData) -> Unit) {

        Glide.with(binding.ivAvatar.context).load(thirdsModel.logo)
            .placeholder(R.drawable.ic_thirds).into(binding.ivAvatar)
        binding.tvThirdsName.text = thirdsModel.name
        binding.tvThirdsCif.text = thirdsModel.cif
        binding.tvThirdsBalance.text = thirdsModel.total_open_balance.toBigDecimal().
            setScale(2, RoundingMode.UP).toString()

        if (thirdsModel.archived == 1) {
            binding.itemThirds.setBackgroundResource(R.drawable.bg_recycler_ic_ar)
        } else {
            binding.itemThirds.setBackgroundResource(R.drawable.bg_recycler_ic)
        }

        itemView.setOnClickListener { onClickListener(thirdsModel) }

    }
}