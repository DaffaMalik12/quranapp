package com.example.quranapp.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val TAG = "AuthViewModel" // Tag untuk logging

    // Sign-Up dengan Email & Password
    fun registerWithEmail(email: String, password: String, onResult: (Boolean) -> Unit) {
        Log.d(TAG, "Memulai registrasi dengan email: $email")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Registrasi berhasil untuk email: $email")
                    onResult(true)
                } else {
                    Log.e(TAG, "Registrasi gagal: ${task.exception?.message}")
                    onResult(false)
                }
            }
    }

    // Login dengan Email & Password
    fun loginWithEmail(email: String, password: String, onResult: (Boolean) -> Unit) {
        Log.d(TAG, "Memulai login dengan email: $email")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "Login berhasil untuk email: $email")
                    onResult(true)
                } else {
                    Log.e("AuthViewModel", "Login gagal: ${task.exception?.message}")
                    onResult(false)
                }
            }
    }

    // Google Sign-In
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        Log.d("AuthViewModel", "Membuat GoogleSignInClient")
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("184071111518-30cldktpc9f222jr0bisd9n26lvva214.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, options)
    }

    fun loginWithGoogle(idToken: String, onResult: (Boolean) -> Unit) {
        Log.d("AuthViewModel", "Memulai login dengan Google, ID Token: $idToken")
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthViewModel", "Login dengan Google berhasil")
                    onResult(true)
                } else {
                    Log.e("AuthViewModel", "Login dengan Google gagal: ${task.exception?.message}")
                    onResult(false)
                }
            }
    }
}