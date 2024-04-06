package com.example.Services

import com.example.dao.MeteostationSensors.MeteostationSenDaoFacade
import com.example.dao.MeteostationSensors.meteostationSensDao
import com.example.models.Meteostations_Sensors
import com.example.models.Request_Meteostations_Sensors

class MeteostationSensService(private val MeteostationSensRepository:MeteostationSenDaoFacade) {

    suspend fun findByInvNumb (sensor_inv_numb: Int): List<Meteostations_Sensors>?{
        return MeteostationSensRepository.findByInvNumb(sensor_inv_numb)
    }
    suspend fun createInvNumb (meteostationsSensors: Request_Meteostations_Sensors): Meteostations_Sensors?{
        return MeteostationSensRepository.createInvNumb(meteostationsSensors)
    }
    suspend fun deleteInvNumb (sensor_inv_numb: Int): Boolean {
        return MeteostationSensRepository.deleteInvNumb(sensor_inv_numb)
    }

}
val meteostationsSensService = meteostationSensDao
