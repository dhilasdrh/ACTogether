package com.dhilasdrh.zerowaste.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.dhilasdrh.zerowaste.viewmodel.AuthViewModel
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val vm = ViewModelProvider(this).get(AuthViewModel::class.java)

        val currentUser = vm.getCurrentUser()
        if (currentUser != null) {
            val userId = currentUser.uid
            vm.getUserDataOnce(userId).observe(this, { user ->
                MyApplication.getPreferencesManager().setUserInfo(user)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            })
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }, 1500)
        }
    }
}