package com.omelchenkoaleks.fitness.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {
    private const val MODE = "mode"
    private const val NAME_PREFERENCE = "preference"

    private lateinit var preferences: SharedPreferences

    fun getPreference(context: Context): SharedPreferences {
        preferences = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
        return preferences
    }

    fun setMode(mode: Int) {
        preferences.edit()
            .putInt(MODE, mode)
            .apply()
    }

    fun getMode(): Int {
        return preferences.getInt(MODE, 0)
    }
}