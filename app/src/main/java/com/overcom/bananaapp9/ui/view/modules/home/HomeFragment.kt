package com.overcom.bananaapp9.ui.view.modules.home

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.data.network.ApiService
import com.overcom.bananaapp9.data.model.OrganizationsData
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.databinding.FragmentHomeBinding
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import com.overcom.bananaapp9.ui.view.modules.HomeActivity
import com.overcom.bananaapp9.ui.view.modules.thirds.ThirdsFragment
import com.overcom.bananaapp9.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[HomeViewModel::class.java]
            }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initUI()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.orgSelector()
    }

    private fun initUI() {
        val viewOrg = layoutInflater.inflate(R.layout.organizations, null)
        val viewToast = layoutInflater.inflate(R.layout.toast_ok, null)

        orgSelector(viewOrg, viewToast, requireActivity())
        intentActivity()
    }

    companion object{
        private lateinit var org: Map<String, Int>

        private fun orgSelector(viewOrg: View, viewToast: View, activity: Activity) {

            CoroutineScope(Dispatchers.IO).launch {

                val apiService : ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
                val call : Response<OrganizationsData> = apiService.listOrganizations()
                val text: TextView = viewToast.findViewById(R.id.toastMessage)
                org = call.body()?.organizations!!.associate { Pair(it.text, it.id) }

                if (call.isSuccessful) {
                    if(call.body()?.organizations?.count()!! <= 1 ){
                        if(call.body()?.organizations?.isEmpty().toString() == "true"){

                            Handler().postDelayed({
                                Toast(activity).apply {
                                    setGravity(Gravity.BOTTOM, 0, 0)
                                    setView(view)
                                    text.text = "Usted no tiene Organización asignada, " +
                                            "por favor comuníquese con un administrador."
                                    Toast.LENGTH_LONG
                                }.show()
                            },4000)  }

                        val orgID = ArrayList(org.values)
                        val orgText = ArrayList(org.keys)

                        preferences.saveOrgId(orgID[0].toString())
                        preferences.saveOrgText(orgText[0])

                    } else { dialogOrganization(viewOrg, activity) }
                }
            }
        }

        fun dialogOrganization(view: View, activity: Activity){
            val builder = AlertDialog.Builder(activity, R.style.CustomDialogTheme)

            builder.setView(view).setIcon(R.drawable.logo_banana)
                .setTitle(
                    Html.fromHtml("<font color='#000000'><b>Seleccionar Organización</b></font>"))
                .setPositiveButton(
                    R.string.OK,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                        startActivity(activity, Intent(activity, HomeActivity::class.java), Bundle())
                    })

            activity.runOnUiThread{
                if(preferences.getFirstRun().equals(true))
                    builder.show()
                preferences.saveFirstRun(false)
            }

            val spinnerOrg = view.findViewById<Spinner>(R.id.listOrganizations)
            val list = ArrayList(org.keys)
            val aaOrganizations = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line,
                list)

            spinnerOrg.adapter = aaOrganizations
            spinnerOrg.setSelection(0)
            spinnerOrg.onItemSelectedListener = object:

                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    preferences.saveOrgId(org[list[position]].toString())
                    preferences.saveOrgText(list[position])

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    return
                }
            }
        }
    }

    private fun intentActivity() {
        val third = binding.terceros
        val thirdsFragment = ThirdsFragment()
        val transaction = requireFragmentManager().beginTransaction()

        third.setOnClickListener {
            transaction.replace(R.id.nav_host_fragment_content_home, thirdsFragment)
           // transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        preferences.saveThirdsType("customer_prospect_archivados")
    }
}