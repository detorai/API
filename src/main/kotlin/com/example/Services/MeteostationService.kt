package com.example.Services

import com.example.dao.Meteostations.MeteostationsDaoFacade
import com.example.dao.Meteostations.meteostationDao
import com.example.models.Meteostation


class MeteostationService(private val MeteostationsRepository: MeteostationsDaoFacade) {
    suspend fun findByStation(stationId: Int): List<Meteostation> {
        return MeteostationsRepository.findByStation(stationId)
    }

    suspend fun deleteStation(stationId: Int): Boolean {
        return MeteostationsRepository.deleteStation(stationId)
    }

}
val meteostationService = MeteostationService(meteostationDao)