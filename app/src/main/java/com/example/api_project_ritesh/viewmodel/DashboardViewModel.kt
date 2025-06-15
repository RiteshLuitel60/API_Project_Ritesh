// ViewModel for dashboard screen - manages  data and selection state
package com.example.api_project_ritesh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_project_ritesh.data.model.DashboardResponse
import com.example.api_project_ritesh.data.model.Entity
import com.example.api_project_ritesh.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _dashboardData = MutableLiveData<Result<DashboardResponse>>()
    val dashboardData: LiveData<Result<DashboardResponse>> = _dashboardData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedEntity = MutableLiveData<Entity>()
    val selectedEntity: LiveData<Entity> = _selectedEntity

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _dashboardData.value = repository.getDashboard(keypass)
            _isLoading.value = false
        }
    }

    fun selectEntity(entity: Entity) {
        _selectedEntity.value = entity
    }
} 