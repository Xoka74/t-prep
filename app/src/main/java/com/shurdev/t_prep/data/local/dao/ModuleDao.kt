package com.shurdev.t_prep.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shurdev.t_prep.data.local.entities.ModuleEntity

@Dao
interface ModuleDao {
    @Query("SELECT * FROM modules")
    suspend fun getModules(): List<ModuleEntity>

    @Query("SELECT * FROM modules WHERE id = :id")
    suspend fun getModuleById(id: String): ModuleEntity?

    @Update
    suspend fun updateModule(module: ModuleEntity)

    @Insert
    suspend fun insertModules(modules: List<ModuleEntity>)
}