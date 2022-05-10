package edu.phystech.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey val city: String,
    @ColumnInfo(name = "first_name") val temrature: Double?,
)