package com.hiltretrofitdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(@PrimaryKey(autoGenerate = true) val id: Int,var name : String)