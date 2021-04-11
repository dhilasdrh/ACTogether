package com.dhilasdrh.zerowaste.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.ActivityProfileBinding
import com.dhilasdrh.zerowaste.ui.profile.ProfileViewModel
import com.dhilasdrh.zerowaste.viewmodel.AuthViewModel
import com.firebase.ui.auth.AuthUI

class ProfileActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        //getUserInfo()
        getUserData()

        supportActionBar?.title = "Account"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getUserData(){
        profileViewModel.displayName.observe(this, {
            binding.tvFullname.text = it
        })
        profileViewModel.email.observe(this, {
            binding.tvEmail.text = it
        })
        profileViewModel.photoUrl.observe(this, {
            if (it != null) {
                Glide.with(this).load(it).into(binding.imgProfile)
            } else {
                Glide.with(this).load(R.mipmap.ic_launcher_round).into(binding.imgProfile)
            }
        })

        //sign-out
        binding.btnSignOut.setOnClickListener {
            this.let { it1 ->
                AuthUI.getInstance()
                    .signOut(it1)
                    .addOnCompleteListener {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
            }
        }
    }

    private fun getUserInfo() {
        val user = MyApplication.getPreferencesManager().getUserInfo()
        binding.tvFullname.text = user.name
        binding.tvEmail.text = user.email

        if (user.photoUrl != "") {
            Glide.with(this).load(user.photoUrl).into(binding.imgProfile)
        } else {
            Glide.with(this).load(R.mipmap.ic_launcher_round).into(binding.imgProfile)
        }

        binding.btnSignOut.setOnClickListener {
            authViewModel.logOutUser()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}