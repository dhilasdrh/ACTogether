package com.dhilasdrh.zerowaste.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.activity.LoginActivity
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.AuthActivity
import com.dhilasdrh.zerowaste.databinding.FragmentProfileBinding
import com.dhilasdrh.zerowaste.viewmodel.AuthViewModel
import com.firebase.ui.auth.AuthUI

class ProfileFragment : Fragment() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
    }
}