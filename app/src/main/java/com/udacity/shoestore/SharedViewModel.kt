package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    private val _logInEvent = MutableLiveData<Boolean>()
    val logInEvent: LiveData<Boolean>
    get() = _logInEvent

    private val _addedShoeEvent = MutableLiveData<Boolean>()
    val addedShoeEvent: LiveData<Boolean>
    get() = _addedShoeEvent

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
    get() = _shoeList

    private val _currentShoe = MutableLiveData<Shoe>()
    val currentShoe: LiveData<Shoe>
    get() = _currentShoe

    init {
        _logInEvent.value = false
        _shoeList.value = mutableListOf()
        currentShoeReset()
    }

    fun login() {
        _logInEvent.value = true
    }

    fun addShoe() {
        _shoeList.value?.add(_currentShoe.value!!)
        currentShoeReset()
        _addedShoeEvent.value = true
    }

    fun completedAddShoe() {
        _addedShoeEvent.value = false
    }

    private fun currentShoeReset() {
        _currentShoe.value = Shoe("", 0.0, "", "", mutableListOf())
    }
}
