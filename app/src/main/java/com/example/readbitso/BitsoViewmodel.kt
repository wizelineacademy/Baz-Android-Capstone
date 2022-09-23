package com.example.readbitso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readbitso.repository.BitsoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitsoViewmodel@Inject constructor(private val bitsoRepositoryImp: BitsoRepository): ViewModel() {

    init {
        viewModelScope.launch{
            bitsoRepositoryImp.getBitsoBooks()
        }
    }



}