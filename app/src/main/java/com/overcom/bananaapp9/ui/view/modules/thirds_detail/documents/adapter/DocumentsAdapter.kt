package com.overcom.bananaapp9.ui.view.modules.thirds_detail.documents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.Archivo
import com.overcom.bananaapp9.R

class DocumentsAdapter (private val listDocuments: List<Archivo>,
                        private val onClickListener:(Archivo) -> Unit) :
    RecyclerView.Adapter<DocumentsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DocumentsViewHolder(layoutInflater.inflate(
            R.layout.item_documents, parent
            , false))
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        val item = listDocuments[position]
        holder.render(item, onClickListener)
    }
    override fun getItemCount(): Int = listDocuments.size

}