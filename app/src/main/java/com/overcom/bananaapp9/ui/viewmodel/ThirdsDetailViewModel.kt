package com.overcom.bananaapp9.ui.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.android.material.tabs.TabLayoutMediator
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.core.Utils.Util
import com.overcom.bananaapp9.data.model.*
import com.overcom.bananaapp9.data.model.repositories.ThirdsDetailRepository
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import com.overcom.bananaapp9.ui.view.modules.thirds_detail.CollectionAdapter
import kotlinx.coroutines.launch

class ThirdsDetailViewModel : ViewModel() {

    private val repository = ThirdsDetailRepository()

    private val _thirdsDetail = MutableLiveData<ThirdsDataDetailItem>()
    var thirdsDetail : MutableLiveData<ThirdsDataDetailItem> = _thirdsDetail

    private val _tabs = MutableLiveData<TabLayout>()
    var tabs:  LiveData<TabLayout> = _tabs

    private val _viewPager2 = MutableLiveData<ViewPager2>()
    var viewPager2:  LiveData<ViewPager2> = _viewPager2

    private val _branch_offices = MutableLiveData<List<BranchOffice>>()
    var branch_offices: MutableLiveData<List<BranchOffice>> = _branch_offices

    private val _listContact = MutableLiveData<List<ThirdContact>>()
    var listContact: MutableLiveData<List<ThirdContact>> = _listContact

    private val _listArchivo = MutableLiveData<List<Archivo>>()
    var listArchivo: MutableLiveData<List<Archivo>> = _listArchivo

    private val _logo = MutableLiveData<String>()
    var logo:  LiveData<String> = _logo

    private val _name = MutableLiveData<String>()
    var name:  LiveData<String> = _name

   /* val _activateTabs = MutableLiveData<Boolean>()
    var activateTabs: LiveData<Boolean> = _activateTabs*/

    init {
        callThirdsDetail()
    }

    fun callThirdsDetail(){

        viewModelScope.launch {
            val call = repository.callThirdsDetail(preferences.getThirdsID())

            if (call.isSuccessful){

                call.body()!!.let { list->
                    _thirdsDetail.value = list
                    _branch_offices.value = list.branch_offices
                    _listContact.value = list.third_contacts
                    _listArchivo.value = list.archivos
                }

            }else{
                Util.callNotSuccessful(call.errorBody()?.string().toString())
            }
        }
    }

    fun tabs(fragmentActivity: FragmentActivity, resources: Resources){
        val collectionAdapter = CollectionAdapter(fragmentActivity)
        val viewPager: ViewPager2 = _viewPager2.value!!
        val tabs: TabLayout = _tabs.value!!
        viewPager2.value?.adapter = collectionAdapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.thirds_details)[position]
        }.attach()
    }

    fun tabs2() {

    }


}