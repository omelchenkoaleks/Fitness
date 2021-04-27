package com.omelchenkoaleks.fitness.utils

import android.content.Context
import android.content.SharedPreferences


//fun saveArray(array: Array<String?>, arrayName: String, mContext: Context): Boolean {
//    val prefs: SharedPreferences = mContext.getSharedPreferences("preferencename", 0)
//    val editor = prefs.edit()
//    editor.putInt(arrayName + "_size", array.size)
//    for (i in array.indices) editor.putString(arrayName + "_" + i, array[i])
//    return editor.commit()
//}
//
//fun loadArray(arrayName: String, mContext: Context): Array<String?> {
//    val prefs: SharedPreferences = mContext.getSharedPreferences("preferencename", 0)
//    val size = prefs.getInt(arrayName + "_size", 0)
//    val array = arrayOfNulls<String>(size)
//    for (i in 0 until size) array[i] = prefs.getString(arrayName + "_" + i, null)
//    return array
//}