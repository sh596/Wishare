package com.example.myapplication.util

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

object FireBaseUtil {
    fun getAuth () : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}