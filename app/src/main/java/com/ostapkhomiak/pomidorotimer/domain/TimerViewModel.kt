package com.ostapkhomiak.pomidorotimer.domain


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel(private val repository: TomatoRepository) : ViewModel() {

    private var timerJob: Job? = null
    private var _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private var _isPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> = _isPaused

    private var _timeElapsed = MutableStateFlow(0)
    val timeElapsed: StateFlow<Int> = _timeElapsed

    private val _timeLimit = MutableStateFlow(0)
    val timeLimit: StateFlow<Int> = _timeLimit

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTimer(minutes: Int) {
        if (_isRunning.value) return // break if timer is already running

        _timeLimit.value = minutes * 60
        _timeElapsed.value = 0
        _isRunning.value = true
        _isPaused.value = false


        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeElapsed.value < _timeLimit.value && _isRunning.value) {
                if (!_isPaused.value) {
                    delay(1000L)
                    _timeElapsed.value++
                } else {
                    delay(100L) // prevent CPU overload because of busy loop
                }

            }
            repository.addTomato(_timeElapsed.value)
            _isRunning.value = false
        }
    }

    fun pauseTimer() {
        if (_isRunning.value) {
            _isPaused.value = true
        }
    }

    fun resumeTimer() {
        if (_isRunning.value) {
            _isPaused.value = false
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun stopTimer() {

        repository.addTomato(_timeElapsed.value)

        _isRunning.value = false
        _isPaused.value = false
        timerJob?.cancel()
        _timeElapsed.value = 0
    }


}