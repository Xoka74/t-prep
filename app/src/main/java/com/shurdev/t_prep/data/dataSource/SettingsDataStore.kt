package com.shurdev.t_prep.data.dataSource

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.shurdev.t_prep.data.local.entities.SettingsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SettingsEntitySerializer : Serializer<SettingsEntity> {
    override val defaultValue: SettingsEntity = SettingsEntity()

    override suspend fun readFrom(input: InputStream): SettingsEntity {
        try {
            return Json.decodeFromString(
                SettingsEntity.serializer(), input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Cannot read json", exception)
        }
    }

    override suspend fun writeTo(
        t: SettingsEntity,
        output: OutputStream,
    ) = withContext(Dispatchers.IO) {
        output.write(
            Json.encodeToString(SettingsEntity.serializer(), t)
                .encodeToByteArray()
        )
    }
}

val Context.settingsEntityDataStore: DataStore<SettingsEntity> by dataStore(
    fileName = "settings",
    serializer = SettingsEntitySerializer
)