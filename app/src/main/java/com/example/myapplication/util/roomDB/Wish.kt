package com.example.myapplication.util.roomDB

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.util.roomDB.RoomUtil.elseMine
import java.io.Serializable

@Entity(tableName = "wishs")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val _id : Int = 0,

    @ColumnInfo(name = "wishContent")
    val wishContent : String = "",

    @ColumnInfo(name = "mine")
    val mine : Int = elseMine,
) : Serializable
