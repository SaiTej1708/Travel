package com.tummoc.travel.mvvm

import androidx.lifecycle.*
import com.tummoc.travel.custom.Event
import com.tummoc.travel.travel.TravelDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TravelViewModel(private val travelRepository: TravelRepository) : ViewModel() {

    private val showProgress = MutableLiveData<Event<String>>()
    val progress : LiveData<Event<String>>
    get() = showProgress

    init {
        showProgress.value = Event("NO")
    }

    fun goByBus(medium : String) {

    }

    fun goByMetro(){

    }

    fun insertItem(travelDataItem: TravelItem) {
        CoroutineScope(Dispatchers.Default).launch {
            travelRepository.insert(travelDataItem)
        }
    }

    suspend fun getAllItems() : LiveData<TravelItem>{
       return  travelRepository.getAllItems()
    }


}


class TravelFactory(private val repository: TravelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TravelViewModel::class.java)){
            return TravelViewModel(repository) as T
        }else {
            throw IllegalArgumentException("Unknown Model Class")
        }
    }
}