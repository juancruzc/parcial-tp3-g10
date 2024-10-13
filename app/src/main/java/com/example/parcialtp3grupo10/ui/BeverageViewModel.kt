package com.example.parcialtp3grupo10.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcialtp3grupo10.model.Beverage
import com.example.parcialtp3grupo10.R

class BeverageViewModel : ViewModel() {
    private val _beverages = MutableLiveData<List<Beverage>>()
    val beverages: LiveData<List<Beverage>> = _beverages

    init {
        _beverages.value = listOf(
            Beverage("Diet Coke", "355ml", 1.99, R.drawable.diet_coke),
            Beverage("Sprite Can", "325ml", 1.50, R.drawable.sprite_can),
            Beverage("Apple & Grape Juice", "2L", 15.99, R.drawable.apple_grape_juice),
            Beverage("Orange Juice", "2L", 15.99, R.drawable.orange_juice),
            Beverage("Coca Cola Can", "325ml", 4.99, R.drawable.coca_cola_can),
            Beverage("Pepsi Can", "330ml", 4.99, R.drawable.pepsi_can)
        )
    }
}
