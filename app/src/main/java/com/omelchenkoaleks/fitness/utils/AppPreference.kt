package com.omelchenkoaleks.fitness.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.util.ArrayList

object AppPreference {
    private const val MODE = "mode"
    private const val DAY = "day"
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

    @SuppressLint("CommitPrefEdits")
    fun saveDays(array: Array<String>, arrayName: String) {
        val editor = preferences.edit()
        editor.putInt(arrayName + "_size", array.size)
        for (i in array.indices) editor.putString(arrayName + "_" + i, array[i])
        editor.apply()
    }

    fun getWorkoutDays(arrayName: String): Array<String?> {
        val size = preferences.getInt(arrayName + "_size", 0)
        val array = arrayOfNulls<String>(size)
        for (i in 0 until size) array[i] = preferences.getString(arrayName + "_" + i, null)
        return array
    }

    fun saveDay(value: String) {
        preferences.edit()
            .putString(DAY, value)
            .apply()
    }
}