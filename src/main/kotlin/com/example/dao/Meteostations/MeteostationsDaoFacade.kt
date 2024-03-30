package com.example.dao.Meteostations

import com.example.models.Meteostation

interface MeteostationsDaoFacade {
    suspend fun findByStation(stationId: Int): List<Meteostation>
    suspend fun createStation(meteostation: Meteostation):Meteostation?
    suspend fun deleteStation(stationId: Int): Boolean
}