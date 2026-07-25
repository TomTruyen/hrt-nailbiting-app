package com.tomtruyen.cusp.data.repository

import com.tomtruyen.cusp.data.local.CheckInDao
import com.tomtruyen.cusp.data.local.CheckInEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckInRepository(private val dao: CheckInDao) {
    fun getAllCheckIns(): Flow<List<CheckInEntity>> = dao.getAllCheckIns()
    
    suspend fun insertCheckIn(
        isBite: Boolean,
        urgeStrength: Int,
        activity: String? = null,
        feeling: String? = null
    ) {
        val entity = CheckInEntity(
            isBite = isBite,
            urgeStrength = urgeStrength,
            activity = activity,
            feeling = feeling
        )
        withContext(Dispatchers.IO) {
            dao.insertCheckIn(entity)
        }
    }
}
