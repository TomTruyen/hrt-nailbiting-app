package com.tomtruyen.cusp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "check_ins")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val isBite: Boolean,
    val urgeStrength: Int, // e.g., 0 = Gone, 1 = Better, 2 = Still strong
    val activity: String?, // e.g., "Working", "Watching TV"
    val feeling: String? // e.g., "Bored", "Stressed"
)
