package com.nasyxnadeem.weatherforecast.screens.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasyxnadeem.weatherforecast.model.Unit
import com.nasyxnadeem.weatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(private val repo: WeatherDbRepository) : ViewModel(){
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())

    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getUnits().distinctUntilChanged()
                .collect {
                    listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG", "EMPTY LIST")
                    } else {
                        _unitList.value = listOfUnits
                    }
                }
        }
    }
}