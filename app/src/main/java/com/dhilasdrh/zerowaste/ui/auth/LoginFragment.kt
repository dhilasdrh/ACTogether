package com.dhilasdrh.zerowaste.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dhilasdrh.zerowaste.MyApplication
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.MainActivity
import com.dhilasdrh.zerowaste.databinding.FragmentLoginBinding
import com.dhilasdrh.zerowaste.utils.Commons
import com.dhilasdrh.zerowaste.utils.gone
import com.dhilasdrh.zerowaste.utils.hideKeyboard
import com.dhilasdrh.zerowaste.utils.visible
import com.dhilasdrh.zerowaste.viewmodel.AuthViewModel
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseAppLifecycleListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class LoginFragment : Fragment() {
    private val TAG = "LOGIN_FRAGMENT"

    companion object {
        const val RC_SIGN_IN = 101
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var vm: AuthViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBtnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_navigate_to_register)
        }

        auth = Firebase.auth

        initViewModel()
        initGoogleSignInClient()

        binding.btnSigninGoogle.setOnClickListener {
            signInGoogle()
        }

        binding.btnLogin.setOnClickListener {
            binding.progressbar.visible()
            hideKeyboard()
            binding.progressbar.gone()
            loginUser()

            if (!isValidated()) {
                binding.progressbar.gone()
                return@setOnClickListener
            } else {
                loginUser()
            }
        }

    }

    private fun initViewModel() {
        vm = ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount) {
        val googleTokenId = googleSignInAccount.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
//        signInWithGoogleCredential(googleAuthCredential)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val googleSignInAccount = task.getResult(ApiException::class.java)
                if (googleSignInAccount != null) {
                    Log.d(TAG, "firebaseAuthWithGoogle:" + googleSignInAccount.id)
                   // getGoogleAuthCredential(googleSignInAccount)

                    firebaseAuthWithGoogle(googleSignInAccount.idToken!!)
                }
            } catch (e: Exception) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                      //  updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                       // updateUI(null)
                    }
                }
    }

    private fun loginUser() {
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()
        vm.loginUser(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = it.result?.user?.uid as String
                vm.getUserDataOnce(userId).observe(viewLifecycleOwner, { user ->
                    MyApplication.getPreferencesManager().setUserInfo(user)
                    startActivity(Intent(context, MainActivity::class.java))
                    binding.progressbar.gone()
                })
            } else {
                val error = it.exception
                when (error) {
                    is FirebaseAuthInvalidUserException -> {
                        binding.tvAlertMessage.text = getString(R.string.message_user_not_exist)
                        binding.tvBtnAlertAction.apply {
                            text = context.getString(R.string.register)
                            setOnClickListener {
                                findNavController().navigate(R.id.action_navigate_to_register)
                            }
                        }
                        binding.layoutAlert.visible()
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        binding.tvAlertMessage.text = getString(R.string.message_wrong_password)
                        binding.tvBtnAlertAction.gone()
                        binding.layoutAlert.visible()
                    }
                }
                binding.progressbar.gone()
                Log.d(TAG, "loginUser: " + error?.message)
            }
        }

    }

    private fun isValidated(): Boolean {
        if (!validateEmail()) return false
        if (!validatePassword()) return false
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
        if (password.isEmpty()) {
            binding.layoutInputPassword.error = getString(R.string.message_field_required)
            binding.inputPassword.requestFocus()
            return false
        } else {
            binding.layoutInputPassword.isErrorEnabled = false
        }
        return true
    }
}