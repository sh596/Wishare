package com.example.myapplication.screen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.screen.main.MainActivity
import com.example.myapplication.util.FireBaseUtil
import com.example.myapplication.util.roomDB.WishDatabase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
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

        if (requestCode == LOGIN_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
            }
        }
    }
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        FireBaseUtil.getAuth().signInWithCredential(credential)?.addOnCompleteListener(this) {
            task ->
            if(task.isSuccessful){
                //로그인 성공 시
                Toast.makeText(this,"로그인에 성공하였습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }

        }
    }
}