package com.ostapkhomiak.pomidorotimer.domain

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ostapkhomiak.pomidorotimer.data.inventory.TomatoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.Locale
import androidx.core.content.edit

class TomatoRepository(context: Context) {
    private val prefs = context.getSharedPreferences("inventory", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val _tomatoes = MutableStateFlow(loadTomatoes())
    val tomatoes: StateFlow<List<TomatoModel>> = _tomatoes

    private fun loadTomatoes(): List<TomatoModel> {
        val json = prefs.getString("tomatoes", null) ?: return emptyList()
        val type = object : TypeToken<List<TomatoModel>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveTomatoes(list: List<TomatoModel>) {
        val json = gson.toJson(list)
        prefs.edit { putString("tomatoes", json) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTomato(timeElapsed: Int) {
        val newList = _tomatoes.value + TomatoModel(
            id = _tomatoes.value.size,
            date = LocalDate.now(),
            timeElapsed = String.format(
                Locale.ENGLISH,
                "%02d:%02d",
                timeElapsed / 60,
                timeElapsed % 60
            )
        )
        _tomatoes.value = newList
        saveTomatoes(newList)
    }
}