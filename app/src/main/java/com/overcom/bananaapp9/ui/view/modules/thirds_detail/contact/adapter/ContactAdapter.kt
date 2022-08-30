package com.overcom.bananaapp9.ui.view.modules.thirds_detail.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.ThirdContact
import com.overcom.bananaapp.thirdsdetail.ui.main.contact.adapter.ContactViewHolder
import com.overcom.bananaapp9.R

class ContactAdapter(private val listContact: List<ThirdContact>) :
    RecyclerView.Adapter<ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(layoutInflater.inflate(
            R.layout.item_contac, parent
            , false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = listContact[position]
        holder.render(item)

    }
    override fun getItemCount(): Int = listContact.size

}