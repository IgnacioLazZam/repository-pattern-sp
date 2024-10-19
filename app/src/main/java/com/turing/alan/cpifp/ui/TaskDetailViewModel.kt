package com.turing.alan.cpifp.ui

import android.icu.text.CaseMap.Title
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.cpifp.data.DefaultTaskRepository
import com.turing.alan.cpifp.data.Task
import com.turing.alan.cpifp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor (
    private val repository: DefaultTaskRepository
): ViewModel() {

    suspend fun readTask(id:String = ""):Task{
        val task = repository.readOne(id)
        return task
    }

}

