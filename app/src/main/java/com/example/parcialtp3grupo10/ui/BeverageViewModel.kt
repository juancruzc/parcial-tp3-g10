package com.example.parcialtp3grupo10.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcialtp3grupo10.model.Beverage
import com.example.parcialtp3grupo10.R

class BeverageViewModel : ViewModel() {
    private val _beverages = MutableLiveData<List<Beverage>>()
    val beverages: LiveData<List<Beverage>> = _beverages

}
