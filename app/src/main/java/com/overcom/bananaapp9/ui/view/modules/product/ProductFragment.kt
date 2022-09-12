package com.overcom.bananaapp9.ui.view.modules.thirds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.overcom.bananaapp9.ui.view.modules.product.adapter.ProductAdapter
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.databinding.FragmentThirdsBinding
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication
import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.data.network.ApiService
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.data.model.ThirdsDataItem
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.thirdsdetail.ThirdsDetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class ProductFragment : Fragment() {

    private var _binding: FragmentThirdsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var filter: String = ""
    var listThirds: MutableList<ThirdsData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initUI()

        return root
    }

    private fun initUI() {
        thirdsCall()
    }

    fun thirdsCall() {
        CoroutineScope(Dispatchers.IO).launch {

            val apiService : ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
            val type_third = "todos"
            val filter = filter
            //val call: Response<ThirdsDataItem> = apiService.listThirds(type_third)

             /*   if(call.isSuccessful){

                    listThirds.clear()
                    listThirds.addAll(call.body()!!.thirds)

                    activity?.runOnUiThread {
                        initRecyclerView()
                    }
                }*/
            }
    }

    private fun initRecyclerView(){

        binding.reciclerThirdsLoading.isVisible = false
        binding.reciclerThirds.isVisible = true
        binding.reciclerThirds.layoutManager = LinearLayoutManager(activity)
        binding.reciclerThirds.adapter = ProductAdapter(listThirds) {
            intentActivity()
            BananaAppAplication.preferences.saveThirdsID(it.id)
        }
    }

    private fun intentActivity() {
            requireFragmentManager().beginTransaction().replace(
                R.id.nav_host_fragment_content_home,
                ThirdsDetailFragment()
            ).commit()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
