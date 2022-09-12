package com.overcom.bananaapp9.ui.view.modules.thirds_detail.thirdsdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.databinding.FragmentThirdsDetailBinding
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.CollectionAdapter
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.contact.ContactFragment
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.documents.DocumentsFragment
import com.overcom.bananaapp9.ui.viewmodel.ThirdsDetailViewModel


class ThirdsDetailFragment : Fragment() {

    private var _binding: FragmentThirdsDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ThirdsDetailViewModel
    lateinit var tabs: TabLayout
    lateinit var viewTabs: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[ThirdsDetailViewModel::class.java]
            }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdsDetailBinding.inflate(inflater, container, false)

        viewTabs = LayoutInflater.from(container!!.context).inflate(R.layout.tabs_thirds_detail,
            container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collectionAdapter = CollectionAdapter(requireActivity())
        tabs = viewTabs.findViewById(R.id.tabLayout)
        val viewP2: ViewPager2 = viewTabs.findViewById(R.id.viewPager2)
        viewP2.adapter = collectionAdapter

        TabLayoutMediator(tabs, viewP2) { tab, position ->
            tab.text = resources.getStringArray(R.array.thirds_details)[position]
        }.attach()

       /* binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                Log.i("Deinis", "No")

                val viewPager = binding.viewPager2
                viewPager.adapter = CollectionAdapter(requireActivity())

                TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
                    tab.text = resources.getStringArray(R.array.thirds_details)[position]
                    Log.i("Deinis1", p0?.position.toString())
                    Log.i("Deinis3", tab.isSelected.toString())
                    Log.i("Deinis2", position.toString())
                }.attach()

                //  binding.viewPager2.adapter = CollectionAdapter(requireActivity())
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.i("Deinis", "Tal Vez")
                val viewPager = binding.viewPager2
                viewPager.adapter = CollectionAdapter(requireActivity())
                Log.i("Deinis5", p0?.position.toString())
                Log.i("Deinis6", p0?.isSelected.toString())
                Log.i("Deinis7", p0?.id.toString())


                TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
                    tab.text = resources.getStringArray(R.array.thirds_details)[position]
                    Log.i("Deinis1", tab.badge.toString())
                    Log.i("Deinis3", tab.isSelected.toString())
                    Log.i("Deinis2", position.toString())
                }.attach()
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.i("Deinis", "Si")
                val viewPager = binding.viewPager2
                viewPager.adapter = CollectionAdapter(requireActivity())

                TabLayoutMediator(binding.tabLayout, viewPager) { _, position ->
                    p0?.text = resources.getStringArray(R.array.thirds_details)[position]
                }.attach()
            }
        })*/

        // set the references of the declared objects above
       /* var pager: ViewPager = viewPager
        var tab: TabLayout = tabs

        // Initializing the ViewPagerAdapter
        val adapter = CollectionAdapter(childFragmentManager)

        // add fragment to the list
        adapter.addFragment(ThirdsDetailFragment(), "Tercero")
        adapter.addFragment(ContactFragment(), "Contactos")
        adapter.addFragment(DocumentsFragment(), "Documentos")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter*/

        // bind the viewPager with the TabLayout.
      //  tab.setupWithViewPager(pager)

        viewModel.thirdsDetail.observe(viewLifecycleOwner) { detail ->

            Glide.with(binding.ivLogo.context).load(detail.third.logo)
            .placeholder(R.drawable.ic_thirds).into(binding.ivLogo)
            binding.subtitleTextView.text = detail.third.name
            binding.cifTextView.text = detail.third.cif
            binding.emailextView.text = detail.third.email
            binding.descView.setText(detail.third.description.toString())
            binding.creditView.setText(detail.third.credit_limit.toBigDecimal().scale().toString())

            if (detail.branch_offices[0].principal == 1){
                binding.addressView.setText(detail.branch_offices[0].address.toString())
                binding.addressView.setAutoSizeTextTypeUniformWithConfiguration(10,100,10, 10)
                binding.cityView.setText(detail.branch_offices[0].city)
                binding.countryView.setText(detail.branch_offices[0].country)
                binding.phoneView.setText(detail.branch_offices[0].phone.toString())
               // binding.phone2View.setText(detail.branch_offices[0].phone_2.toString())
            }
        }
    }

    private fun initUI(){
        binding.contact.setOnClickListener { contac() }
        binding.document.setOnClickListener { document() }
    }

   /* private fun callThidsDetail(){

                if(call.isSuccessful){

                    Glide.with(binding.ivLogo.context).load(thirdsDetail.third.logo)
                        .placeholder(R.drawable.ic_thirds).into(binding.ivLogo)
                    binding.subtitleTextView.text = binding.subtitleTextView.text!!
                        .ifEmpty { thirdsDetail.third.name}
                    binding.cifTextView.text = binding.cifTextView.text!!
                        .ifEmpty { thirdsDetail.third.cif }
                    binding.emailextView.text = binding.emailextView.text!!
                        .ifEmpty { thirdsDetail.third.email }
                    binding.descView.setText(binding.descView.text!!
                        .ifEmpty { thirdsDetail.third.description.toString() })
                    binding.creditView.setText(binding.creditView.text!!
                        .ifEmpty { thirdsDetail.third.credit_limit
                            .toBigDecimal().setScale(2, ROUND_HALF_UP).toString() })

                    if(branch_offices[0].principal == 1){
                        binding.addressView.setText(binding.addressView.text!!
                            .ifEmpty { branch_offices[0].address.toString() })
                        binding.cityView.setText(binding.cityView.text!!
                            .ifEmpty { branch_offices[0].city })
                        binding.countryView.setText(binding.countryView.text!!
                            .ifEmpty { branch_offices[0].country })
                        binding.phoneView.setText(binding.phoneView.text!!
                            .ifEmpty { branch_offices[0].phone.toString() })
                        // binding.phone2View.setText( binding.phone2View.text!!.ifEmpty { branch_offices[0].phone_2.toString() })
                    }

                    listContact.clear()
                    listContact.addAll(thirdsDetail!!.third_contacts)

                    listDocuments.clear()
                    listDocuments.addAll(thirdsDetail!!.archivos)
           }
        }*/

    private fun contac() {
        requireFragmentManager().beginTransaction().replace(
            R.id.nav_host_fragment_content_home,
            ContactFragment()
        ).commit()
    }

    private fun document() {
        requireFragmentManager().beginTransaction().replace(
            R.id.nav_host_fragment_content_home,
            DocumentsFragment()
        ).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}