package com.overcom.bananaapp.thirdsdetail.ui.main.contact.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.ThirdContact
import com.overcom.bananaapp9.databinding.ItemContacBinding

class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemContacBinding.bind(view)

    fun render(contactModel: ThirdContact) {

        binding.tvContacsName.text = contactModel.name

        binding.tvContacsCargo.text = contactModel.charge_name
        binding.tvContacsEmail.text = contactModel.email
        binding.tvContacsPhone.text = contactModel.phone.toString()
        //binding.tvContacsPhone2.text = contactModel.phone_2!!.toString()

    }
}