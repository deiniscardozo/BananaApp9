package com.overcom.bananaapp9.ui.viewmodel

import androidx.lifecycle.*
import com.overcom.bananaapp9.core.Utils.Util
import com.overcom.bananaapp9.data.model.*
import com.overcom.bananaapp9.data.model.repositories.ThirdsDetailRepository
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import kotlinx.coroutines.launch

class ThirdsDetailViewModel : ViewModel() {

    private val repository = ThirdsDetailRepository()

    private val _thirdsDetail = MutableLiveData<ThirdsDataDetailItem>()
    var thirdsDetail : MutableLiveData<ThirdsDataDetailItem> = _thirdsDetail

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
                }

            }else{
                Util.callNotSuccessful(call.errorBody()?.string().toString())
            }
        }
    }

    /*fun tabs(fragmentActivity: FragmentActivity, resources: Resources){
        val collectionAdapter = CollectionAdapter(fragmentActivity)
        val viewPager: ViewPager2 = _viewPager2.value!!
        val tabs: TabLayout = _tabs.value!!
        viewPager2.value?.adapter = collectionAdapter

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.thirds_details)[position]
        }.attach()
    }

    fun tabs2() {

    }*/


}