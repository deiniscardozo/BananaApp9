package com.overcom.bananaapp9.ui.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overcom.bananaapp9.core.Utils.Util
import com.overcom.bananaapp9.data.model.repositories.HomeRepository
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = HomeRepository()

    private val _org = MutableLiveData<Map<String, Int>>()
    var org: LiveData<Map<String, Int>> = _org

    private val _orgID = MutableLiveData<ArrayList<Int>>()
    var orgID: LiveData<ArrayList<Int>> = _orgID

    private val _orgText = MutableLiveData<ArrayList<String>>()
    var orgText: LiveData<ArrayList<String>> = _orgText

    fun orgSelector() {
        viewModelScope.launch {
            val call = repository.orgSelector()
            _org.value = call.body()?.organizations!!.associate { Pair(it.text, it.id) }

            if(call.isSuccessful){
                if(call.body()?.organizations?.count()!! <= 1 ){
                    if(call.body()?.organizations?.isEmpty().toString() == "true"){

                        Handler().postDelayed({
                            Util.callNotSuccessful("Usted no tiene Organización asignada, " +
                                    "por favor comuníquese con un administrador.")
                        },4000) }

                    _orgID.value = org.value?.values?.let { ArrayList(it) }
                    _orgText.value = org.value?.keys?.let { ArrayList(it) }

                    preferences.saveOrgId(orgID.value.toString())
                    preferences.saveOrgText(orgText.value.toString())

                    } else {/* dialogOrganization() */}
            } else {Util.callNotSuccessful(call.errorBody()!!.string())}
        }
    }
}