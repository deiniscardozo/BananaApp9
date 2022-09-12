package com.overcom.bananaapp9.ui.view.modules.thirds_detail

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.contact.ContactFragment
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.documents.DocumentsFragment
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.thirdsdetail.ThirdsDetailFragment

class CollectionAdapter(fm: FragmentActivity) :
    FragmentStateAdapter(fm) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        LayoutInflater.from(recyclerView.context).inflate(R.layout.tabs_thirds_detail, recyclerView, false)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ThirdsDetailFragment()
            1 -> ContactFragment()
            2 -> DocumentsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
