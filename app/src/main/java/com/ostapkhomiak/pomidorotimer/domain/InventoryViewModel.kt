package com.ostapkhomiak.pomidorotimer.domain



import androidx.lifecycle.ViewModel
import com.ostapkhomiak.pomidorotimer.data.inventory.TomatoModel
import kotlinx.coroutines.flow.StateFlow

class InventoryViewModel(private val repository: TomatoRepository) : ViewModel() {

    val inventoryList: StateFlow<List<TomatoModel>> = repository.tomatoes

    fun removeTomato(tomato: TomatoModel){
        repository.removeTomato(tomato)
    }
}
