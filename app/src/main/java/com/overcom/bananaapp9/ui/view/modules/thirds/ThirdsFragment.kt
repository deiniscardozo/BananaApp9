package com.overcom.bananaapp9.ui.view.modules.thirds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.core.Utils.Util
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.databinding.FragmentThirdsBinding
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import com.overcom.bananaapp9.ui.view.modules.thirds.adapter.ThirdsAdapter
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.thirdsdetail.ThirdsDetailFragment
import com.overcom.bananaapp9.ui.viewmodel.ThirdsViewModel

class ThirdsFragment : Fragment() {
    private var _binding: FragmentThirdsBinding? = null
    private val binding get() = _binding!!
    var listThirds: MutableList<ThirdsData> = mutableListOf()
    private lateinit var viewModel: ThirdsViewModel
    private lateinit var typeThirds: String
    private var positions: Int? = null
    private var positionsR: Int? = null
    private var totalItemCount: Int? = null
    private var mAdapter: ThirdsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[ThirdsViewModel::class.java]
            }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.thirdsCall(typeThirds, positionsR.toString())
            binding.swipeRefreshLayout.isRefreshing = false
        }

        mAdapter = ThirdsAdapter(listThirds) {
            Util.intentFragment(ThirdsDetailFragment(), childFragmentManager, R.id.fragment_thirds)
            viewModel._activateFilter.value = false
            preferences.saveThirdsID(it.id)
        }

        binding.reciclerThirds.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            preferences.savePosition(0)

            clearOnScrollListeners()

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    viewModel.listThirds.observe(viewLifecycleOwner) {
                        mAdapter!!.setItems(list = it)
                        totalItemCount = it.size
                    }

                    if (totalItemCount == 100) {
                        viewModel.thirdsCall(typeThirds, positions.toString())
                    } else {
                        preferences.savePosition(0)
                        invalidate()
                    }
                }
            })
        }

        viewModel.type_third.observe(viewLifecycleOwner) {
            typeThirds = it
        }

        viewModel.limit.observe(viewLifecycleOwner) { limit ->

            positions = preferences.getPosition() + limit
            preferences.savePosition(positions!!)

            if(preferences.getPosition() == 0){
                positionsR = preferences.getPosition()
            }else{
                positionsR = 0
            }
        }

        viewModel.load.observe(viewLifecycleOwner) { show ->
            binding.reciclerThirdsLoading.isVisible = show
        }

        viewModel.listThirds.observe(viewLifecycleOwner) {
            mAdapter!!.setItems(list = it)
        }

        viewModel.thirdsCall("customer_prospect_archivados", "0")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        preferences.savePosition(0)
    }

}