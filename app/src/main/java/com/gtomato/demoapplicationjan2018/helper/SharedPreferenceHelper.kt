package com.gtomato.demoapplicationjan2018.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.gtomato.demoapplicationjan2018.DemoApplicationJan2018
import java.util.*

/**
 * Created by Geoffrey Ma on 2018-01-16.
 */
class SharedPreferenceHelper(
        val context: Context
) {

    private val SHARED_PREFERENCE_SKIPPED_QUESTION = "SHARED_PREFERENCE_SKIPPED_QUESTION";
    private val SHARED_PREFERENCE_IS_SKIPPED_READING = "SHARED_PREFERENCE_IS_SKIPPED_READING";
    private val SHARED_PREFERENCE_BOOKMARK_PAGE = "SHARED_PREFERENCE_BOOKMARK_PAGE";
    private val SHARED_PREFERENCE_IS_FINISHED = "SHARED_PREFERENCE_IS_FINISHED";
    private val SHARED_PREFERENCE_IS_FIRST_TIME = "SHARED_PREFERENCE_IS_FIRST_TIME";
    private val SHARED_PREFERENCE_IS_REDIRECT = "SHARED_PREFERENCE_IS_REDIRECT";

    companion object {
        var sInstance = SharedPreferenceHelper(DemoApplicationJan2018.instance()!!)
    }

    fun defaultPrefs(): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context);


    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit({ it.putString(key, value) })
            is Int -> edit({ it.putInt(key, value) })
            is Boolean -> edit({ it.putBoolean(key, value) })
            is Float -> edit({ it.putFloat(key, value) })
            is Long -> edit({ it.putLong(key, value) })
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    operator inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    var isEmptyQuestion: Boolean?
        get() = defaultPrefs()[SHARED_PREFERENCE_SKIPPED_QUESTION, false]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_SKIPPED_QUESTION] = value
        }

    var isSkippedReading: Boolean?
        get() = defaultPrefs()[SHARED_PREFERENCE_IS_SKIPPED_READING, false]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_IS_SKIPPED_READING] = value
        }

    var pageBookmark: Int?
        get() = defaultPrefs()[SHARED_PREFERENCE_BOOKMARK_PAGE, -1]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_BOOKMARK_PAGE] = value
        }

    var isFinished: Boolean?
        get() = defaultPrefs()[SHARED_PREFERENCE_IS_FINISHED, false]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_IS_FINISHED] = value
        }

    var isFirstTime: Boolean?
        get() = defaultPrefs()[SHARED_PREFERENCE_IS_FIRST_TIME, true]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_IS_FIRST_TIME] = value
        }

    var isRedirect: Boolean?
        get() = defaultPrefs()[SHARED_PREFERENCE_IS_REDIRECT, false]
        set(value) {
            defaultPrefs()[SHARED_PREFERENCE_IS_REDIRECT] = value
        }
}