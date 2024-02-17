package com.example.dao.Meteostations

import com.example.models.Meteostation
import com.example.models.MeteostationMain
import com.example.models.Meteostations

interface MeteostationsDaoFacade {
    suspend fun findByStation(): List<Meteostation>
    suspend fun createStation(meteostation: Meteostation):Meteostation?
    suspend fun deleteStation(stationId: Int): Boolean
}