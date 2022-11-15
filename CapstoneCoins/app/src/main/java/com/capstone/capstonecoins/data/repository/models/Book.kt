package com.capstone.capstonecoins.data.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idBook") val idBook: Int,
    @ColumnInfo(name = "id") val id: String
) : Serializable
