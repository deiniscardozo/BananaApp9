package com.overcom.bananaapp9.core.Utils

import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.overcom.bananaapp9.R

object Util: AppCompatActivity(){

    fun callNotSuccessful(call: String){
        val view = layoutInflater.inflate(R.layout.toast_error, null)
        val text: TextView = view.findViewById(R.id.toastMessage)

        Toast(this).apply {
            setGravity(Gravity.CENTER, 0, 0)
            setView(view)
            text.text = call
        }.show()
    }

    fun intentFragment(fragment: Fragment, manager: FragmentManager, viewInt: Int) {
        val transaction = manager.beginTransaction()

        transaction.replace(viewInt, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}