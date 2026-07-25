package com.tomtruyen.cusp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDao {
    @Insert
    fun insertCheckIn(checkIn: CheckInEntity)

    @Query("SELECT * FROM check_ins ORDER BY timestamp DESC")
    fun getAllCheckIns(): Flow<List<CheckInEntity>>
    
    @Query("SELECT * FROM check_ins WHERE timestamp >= :since ORDER BY timestamp DESC")
    fun getCheckInsSince(since: Long): Flow<List<CheckInEntity>>
}
