package com.dhilasdrh.zerowaste.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dhilasdrh.zerowaste.LoginActivity
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        profileViewModel.displayName.observe(viewLifecycleOwner, {
            binding.tvFullname.text = it
        })
        profileViewModel.email.observe(viewLifecycleOwner, {
            binding.tvEmail.text = it
        })
        profileViewModel.photoUrl.observe(viewLifecycleOwner, {
            if (it != null){
                Glide.with(this).load(it).into(binding.imgProfile)
            } else {
                Glide.with(this).load(R.mipmap.ic_launcher_round).into(binding.imgProfile)
            }
        })

        //sign-out
        binding.btnSignOut.setOnClickListener {
            context?.let { it1 ->
                AuthUI.getInstance()
                    .signOut(it1)
                    .addOnCompleteListener {
                        startActivity(Intent(context, LoginActivity::class.java))
                    }
            }
        }

        return binding.root
    }
}