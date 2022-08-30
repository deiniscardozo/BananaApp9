package com.overcom.bananaapp9.ui.view.modules.thirds_detail.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.contact.adapter.ContactAdapter
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.databinding.FragmentContactBinding
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.thirdsdetail.ThirdsDetailFragment

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        val binding = FragmentContactBinding.bind(view)
      //  val list = ThirdsDetailFragment.listContact

        binding.reciclerContact.isVisible = true
        binding.reciclerContact.layoutManager = LinearLayoutManager(this.context)
 //       binding.reciclerContact.adapter = ContactAdapter(list)

        return view
    }

}