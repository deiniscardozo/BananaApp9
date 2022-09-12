package com.overcom.bananaapp9.ui.viewmodel

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.core.Utils.Util
import com.overcom.bananaapp9.data.model.ThirdsData
import com.overcom.bananaapp9.data.model.repositories.ThirdsRepository
import kotlinx.coroutines.launch


class ThirdsViewModel : ViewModel() {

    private val repository = ThirdsRepository()

    private val _listThirds = MutableLiveData<List<ThirdsData>>()
    var listThirds: LiveData<List<ThirdsData>> = _listThirds

    private val _type_third = MutableLiveData<String>()
    var type_third: LiveData<String> = _type_third

    val _position = MutableLiveData<Int>()
    var position: LiveData<Int> = _position

    private val _limit = MutableLiveData<Int>()
    var limit: LiveData<Int> = _limit

    private val _load = MutableLiveData<Boolean>()
    var load: LiveData<Boolean> = _load

    val _activateFilter = MutableLiveData<Boolean>()
    var activateFilter: LiveData<Boolean> = _activateFilter

    val _filter = MutableLiveData<String>()
    var filter: LiveData<String> = _filter

    fun thirdsCall(typeThirds: String) {
        _type_third.value = typeThirds
        _limit.value = 1
        _load.value = true
        _position.value = 0

        if(filter.value.isNullOrEmpty())
            _filter.value = ""

      /*  if(listThirds.value.isNullOrEmpty()) {
            _position.value = 0
        } else {
            _position.value = listThirds.value!!.size + 1
        }*/

        viewModelScope.launch {
            Log.i("Deinis", type_third.value.toString())
            Log.i("Deinis1", limit.value.toString())
            Log.i("Deinis2", position.value.toString())

           val call = repository.thirdsCall(type_third.value.toString(), filter.value.toString(),
                limit.value.toString(), position.value.toString())

            if (call.isSuccessful){
                call.body()!!.thirds.let { list->

                    if(listThirds.value.isNullOrEmpty()) {
                        _listThirds.value = list
                        _position.value = 0
                    } else {
                        _listThirds.value = listThirds.value?.plus(list)
                        _position.value = listThirds.value!!.size + 1
                    }

                    _load.value = false
                    _activateFilter.value = true
                    _filter.value = ""
                    _limit.value = list.size
                }

            }else{
                Util.callNotSuccessful(call.errorBody()?.string().toString())
            }
        }
    }

    fun filterThirds(context: Context) {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)
        val filterThirds = R.array.thirds_filter

        builder.setIcon(R.drawable.logo_banana)
            .setTitle(R.string.choose_an_option)
            .setSingleChoiceItems(filterThirds, -1,
                DialogInterface.OnClickListener { dialog, which ->

                    dialog.cancel()

                    if (which == 0) {
                        _type_third.value = "customer"
                    } else {
                        if (which == 2) {
                            _type_third.value = "prospect"
                        } else {
                            if (which == 1) {
                                _type_third.value = "customer_prospect"
                            } else {
                                if (which == 3){
                                    _type_third.value = "archivados"
                                } else {
                                    if (which == 4)
                                        _type_third.value = "customer_prospect_archivados"
                                    }
                            }
                        }
                    }
                    thirdsCall(type_third.value.toString())
                })

        builder.create()
        builder.show()
    }
}