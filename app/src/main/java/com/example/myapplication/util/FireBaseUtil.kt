package com.example.myapplication.util

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FireBaseUtil {
    fun getAuth () : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    fun getDB() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
}