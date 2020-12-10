package com.azatberdimyradov.kotlincountres.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azatberdimyradov.kotlincountres.model.Country
import com.azatberdimyradov.kotlincountres.service.CountryDatabase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class CountryViewModel(application: Application): BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}