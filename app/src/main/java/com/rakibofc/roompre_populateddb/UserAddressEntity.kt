package com.rakibofc.roompre_populateddb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class UserAddressEntity(

    @PrimaryKey
    val id: Int,
    val address: String
)