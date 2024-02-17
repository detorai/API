package com.example.Services

import com.example.dao.Meteostations.MeteostationsDaoFacade
import com.example.dao.Meteostations.meteostationDao
import com.example.dao.Sensors.sensorDao
import com.example.models.Measurement_type
import com.example.models.Meteostation
import com.example.models.MeteostationMain
import com.example.models.Meteostations
import org.postgresql.geometric.PGpoint


class MeteostationService(private val MeteostationsRepository: MeteostationsDaoFacade) {
    suspend fun findByStation(): List<Meteostation> {
        return MeteostationsRepository.findByStation()
    }

    suspend fun deleteStation(stationId: Int): Boolean {
        return MeteostationsRepository.deleteStation(stationId)
    }

}
val meteostationService = MeteostationService(meteostationDao)