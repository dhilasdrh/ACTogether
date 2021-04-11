package com.dhilasdrh.zerowaste.utils

import android.content.SharedPreferences
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.model.User

class PreferencesManager {
    companion object{
        private lateinit var preferences: SharedPreferences

        fun initPreferences(): PreferencesManager {
            preferences = MyApplication.getInstance().getSharedPreferences()
            return PreferencesManager()
        }
    }

    fun setUserInfo(user: User) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.apply {
            putBoolean(Constants.PREF_IS_LOGIN, true)
            putString(Constants.PREF_USERID, user.userId)
            putString(Constants.PREF_NAME, user.name)
            putString(Constants.PREF_EMAIL, user.email)
            putString(Constants.PREF_PHONE_NUMBER, user.phoneNumber)
            putString(Constants.PREF_PHOTO_URL, user.photoUrl)
        }
        editor.apply()
    }

    fun getUserInfo(): User {
        return User(
            userId = preferences.getString(Constants.PREF_USERID, "") as String,
            name = preferences.getString(Constants.PREF_NAME, "") as String,
            email = preferences.getString(Constants.PREF_EMAIL, "") as String,
            phoneNumber = preferences.getString(Constants.PREF_PHONE_NUMBER, "") as String,
            photoUrl = preferences.getString(Constants.PREF_PHOTO_URL, "") as String
        )
    }

    fun logOutUser() {
        val editor = preferences.edit()
        editor.apply{
            remove(Constants.PREF_IS_LOGIN)
            remove(Constants.PREF_USERID)
            remove(Constants.PREF_NAME)
            remove(Constants.PREF_EMAIL)
            remove(Constants.PREF_PHONE_NUMBER)
            remove(Constants.PREF_PHOTO_URL)
        }
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(Constants.PREF_IS_LOGIN, false)
    }

}