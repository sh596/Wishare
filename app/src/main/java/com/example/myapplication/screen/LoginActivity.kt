package com.example.myapplication.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.util.FireBaseUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private val LOGIN_CODE = 101
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleClient = GoogleSignIn.getClient(this, gso)

        binding.signInButton.setOnClickListener {
            logIn()
        }

    }
    fun logIn(){
        val intent = googleClient?.signInIntent
        startActivityForResult(intent, LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("google login", "-2")
        if (requestCode == LOGIN_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("google login", "0")
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.d("google login", "-1")
            }
        }
    }
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?){
        Log.d("google login", "1")
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        Log.d("google login", "2")
        FireBaseUtil.getAuth().signInWithCredential(credential)?.addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                //로그인 성공 시
                Log.d("google login", "성공")
                Toast.makeText(this,"로그인에 성공하였습니다", Toast.LENGTH_SHORT).show()
            }else{
                Log.d("google login", "실패")
                Toast.makeText(this,"로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }

        }
    }
}