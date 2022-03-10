package com.stathis.unipiapp.database

import androidx.room.*
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement

@Dao
interface UnipiDao {

    @Query("SELECT * FROM Announcements")
    suspend fun getAll(): List<DeptAnnouncement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(announcement: List<DeptAnnouncement>)

    @Query("DELETE from Announcements")
    suspend fun deleteAll()

    @Update
    suspend fun updateAnnouncement(announcement: DeptAnnouncement)
}