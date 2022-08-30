package com.overcom.bananaapp9.ui.view.modules.thirds_detail.documents.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.data.model.Archivo
import com.overcom.bananaapp9.databinding.ItemDocumentsBinding

class DocumentsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDocumentsBinding.bind(view)

    fun render(documentsModel: Archivo, onClickListener:(Archivo) -> Unit) {

        binding.tvDocumentsName.text = documentsModel.nombre
        binding.tvDocumentsArchivo.text = documentsModel.archivo

        itemView.setOnClickListener { onClickListener(documentsModel) }
    }
}