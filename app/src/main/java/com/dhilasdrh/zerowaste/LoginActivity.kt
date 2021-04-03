package com.dhilasdrh.zerowaste

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            // already signed in
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // not signed in
            signIn()
        }
    }

    private fun signIn() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.FacebookBuilder().build()
                    )
                )
                .setIsSmartLockEnabled(false)
                .setLogo(R.mipmap.ic_launcher_round)
                .build(), RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                //Sign in failed
                if (response == null) {
                    // User pressed back button
                    Snackbar.make(findViewById(android.R.id.content),"Sign in Cancelled", Snackbar.LENGTH_LONG).show()
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(findViewById(android.R.id.content),"No Internet Connection", Snackbar.LENGTH_LONG).show()
                    return
                }

                Snackbar.make(findViewById(android.R.id.content),"Unknown Error", Snackbar.LENGTH_LONG).show()
                Log.e("error", "Sign-in error: ", response.error)
            }
        }
    }
}