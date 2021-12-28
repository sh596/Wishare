package com.example.myapplication.screen.main

import android.util.Log
import com.example.myapplication.util.roomDB.RoomUtil.elseMine
import com.example.myapplication.util.roomDB.RoomUtil.mine
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TodayWishRepository(private val wishDao : WishDao) : TodayWishRepositoryInterface {
    val db = FirebaseFirestore.getInstance()

    override suspend fun getWishCount(): Int {
        var maxCount = -1

        db.collection("wish").document("information").get()
            .addOnSuccessListener {
                maxCount = Integer.parseInt(it.data!!["information"].toString())
            }.await()

        Log.d("todayWish", "최대값 가져옴")
        return maxCount
    }

    override suspend fun getAlreadyWishList(): Array<Wish> {
        return wishDao.getAll()
    }

    override suspend fun getWishElse(): Array<Wish> {
        return wishDao.getMineWish(elseMine)
    }

    override suspend fun getWish(index: Int): String {
        var result : String? = null

        Log.d("randomIndex", index.toString())
        db.collection("wish").document(index.toString()).get()
            .addOnSuccessListener {
                if(it.data != null) {
                    result = (it.data!!.get("wish") as Map<String, String>)["value"]
                }
            }.await()

        return result!!
    }

    override suspend fun getAllWish(): List<String> {
        var result = mutableListOf<String>()

        db.collection("wish").orderBy("wish").get()
            .addOnSuccessListener { docs ->
                for(doc in docs){
                    if (doc.id == "information") continue

                    Log.d("todayWish", "${doc.id}, ${doc.data}")
                    result.add(doc.data["wish"].toString())
                }
            }.await()

        return result
    }

    override suspend fun saveGotWish(wish : Wish) {
        wishDao.insert(wish)
    }
}