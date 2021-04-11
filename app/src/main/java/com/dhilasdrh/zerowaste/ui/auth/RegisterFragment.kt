package com.dhilasdrh.zerowaste.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.FragmentRegisterBinding
import com.dhilasdrh.zerowaste.model.User
import com.dhilasdrh.zerowaste.utils.Commons
import com.dhilasdrh.zerowaste.utils.gone
import com.dhilasdrh.zerowaste.utils.hideKeyboard
import com.dhilasdrh.zerowaste.utils.visible
import com.dhilasdrh.zerowaste.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterFragment : Fragment() {
    private val MY_TAG = "REGISTER_FRAGMENT"

    private lateinit var vm: AuthViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.tvBtnLogin.setOnClickListener { findNavController().navigateUp() }

        initViewModel()

        binding.btnRegister.setOnClickListener {
            binding.progressbar.visible()
            hideKeyboard()

            if(!isValidated()){
                binding.progressbar.gone()
                return@setOnClickListener
            } else {
                registerUser()
            }
        }
    }

    private fun initViewModel() {
        vm = ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    private fun registerUser() {
        val name = binding.inputName.text.toString().trim()
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()
        vm.registerUser(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                val userId = it.result?.user?.uid as String
                val user = User(
                    userId = userId,
                    name = name,
                    email = email,
                )

                vm.createUser(user).addOnSuccessListener {
                    binding.tvAlertMessage.text = getString(R.string.message_register_success)
                    vm.logOutUser()
                    binding.layoutAlert.visible()
                    binding.progressbar.gone()
                }
            } else {
                val error = it.exception
                when (error) {
                    is FirebaseAuthUserCollisionException -> {
                        binding.layoutInputEmail.error = getString(R.string.message_email_exist)
                        binding.inputEmail.requestFocus()
                    }
                }
                binding.progressbar.gone()
                Log.d(MY_TAG, "registerUser: " + error?.message)
            }
        }
    }

    private fun isValidated(): Boolean {
        if (!validateName()) return false
        if (!validateEmail()) return false
        if (!validatePassword()) return false
        if (!validatePasswordConfirm()) return false
        return true
    }

    private fun validateName(): Boolean {
        val name = binding.inputName.text.toString().trim()
        if (name.isEmpty()) {
            binding.layoutInputName.error = getString(R.string.message_field_required)
            binding.inputName.requestFocus()
            return false
        } else {
            binding.layoutInputName.isErrorEnabled = false
        }
        return true
    }

    private fun validateEmail(): Boolean {
        val email = binding.inputEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.layoutInputEmail.error = getString(R.string.message_field_required)
            binding.inputEmail.requestFocus()
            return false
        } else if (!Commons.isValidEmail(email)) {
            if (email.length > 10) {
                binding.layoutInputEmail.error = getString(R.string.message_email_invalid)
                binding.inputEmail.requestFocus()
            }
            return false
        } else {
            binding.layoutInputEmail.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        val password = binding.inputPassword.text.toString().trim()
        when {
            password.isEmpty() -> {
                binding.layoutInputPassword.error = getString(R.string.message_field_required)
                binding.inputPassword.requestFocus()
                return false
            }
            password.length < 6 -> {
                binding.layoutInputPassword.error = getString(R.string.message_password_length)
                binding.inputPassword.requestFocus()
                return false
            }
            else -> {
                binding.layoutInputPassword.isErrorEnabled = false
            }
        }
        return true
    }

    private fun validatePasswordConfirm(): Boolean {
        val password = binding.inputPassword.text.toString().trim()
        val passwordConfirm = binding.inputPasswordConfirm.text.toString().trim()
        if (passwordConfirm != password) {
            binding.layoutInputPasswordConfirm.error = getString(R.string.message_password_confirm_incorrect)
            binding.inputPasswordConfirm.requestFocus()
            return false
        } else {
            binding.layoutInputPasswordConfirm.isErrorEnabled = false
        }
        return true
    }
}